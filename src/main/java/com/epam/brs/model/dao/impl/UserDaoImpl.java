package com.epam.brs.model.dao.impl;

import com.epam.brs.model.dao.UserDao;
import com.epam.brs.model.dao.DaoException;
import com.epam.brs.model.entity.User;
import com.epam.brs.model.entity.enumType.UserRole;
import com.epam.brs.model.pool.ConnectionPool;
import com.epam.brs.util.Encryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.intellij.lang.annotations.Language;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger();

    private static final UserDao instance = new UserDaoImpl();

    private static final String FILE_PATH_BASE = "C:/ProgramData/MySQL/MySQL Server 5.7/Uploads/users";

    @Language("MySQL")
    private static final String ADD_USER_SQL_QUERY = "INSERT INTO users(login, passwordHash, name, surname, email, role) VALUES (?, ?, ?, ?, ?, ?)";
    @Language("MySQL")
    private static final String CONTAINS_LOGIN_SQL_QUERY = "SELECT 1 FROM users WHERE login=?";
    @Language("MySQL")
    private static final String FIND_USER_SQL_QUERY = "SELECT id_user, passwordHash, name, surname, email, role, photo_path FROM users WHERE login=?";
    @Language("MySQL")
    private static final String FIND_USER_EMAIL_SQL_QUERY = "SELECT email FROM users WHERE id_user=?";
    @Language("MySQL")
    private static final String UPDATE_PHOTO_SQL_QUERY = "UPDATE users SET photo_path=? WHERE login=?";
    @Language("MySQL")
    private static final String FIND_ALL_SQL_QUERY = "SELECT id_user, login, name, surname, email, role, is_blocked, registered_at FROM users";
    @Language("MySQL")
    private static final String DOWNLOAD_IN_JSON_SQL_QUERY = "SELECT\n" +
            "    CONCAT('[',\n" +
            "           GROUP_CONCAT(\n" +
            "                   CONCAT(\"{id_user:'\",id_user,\"'\"),\n" +
            "                   CONCAT(\",login:'\",login,\"'\"),\n" +
            "                   CONCAT(\",passwordHash:'\",passwordHash,\"'\"),\n" +
            "                   CONCAT(\",name:'\",name,\"'\"),\n" +
            "                   CONCAT(\",surname:'\",surname,\"'\"),\n" +
            "                   CONCAT(\",email:'\",email,\"'\"),\n" +
            "                   CONCAT(\",phone:'\",phone,\"'\"),\n" +
            "                   CONCAT(\",role:'\",role,\"'\"),\n" +
            "                   CONCAT(\",is_blocked:'\",is_blocked,\"'\"),\n" +
            "                   CONCAT(\",registered_at:'\",registered_at,\"'\"),\n" +
            "                   CONCAT(\",photo_path:'\",photo_path,\"'}\")\n" +
            "               )\n" +
            "        ,\"]\")\n" +
            "        " +
            "AS json FROM users\n" +
            "INTO OUTFILE ?";

    private UserDaoImpl() {
    }

    public static UserDao getInstance() {
        return instance;
    }

    @Override
    public boolean downloadTableAsJSON() throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(DOWNLOAD_IN_JSON_SQL_QUERY)) {
            String filePath = FILE_PATH_BASE + LocalDateTime.now().hashCode() + ".json";
            statement.setString(1, filePath);
            statement.executeQuery();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return true;
    }

    @Override
    public boolean containsLogin(String login) throws DaoException {
        boolean contains = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(CONTAINS_LOGIN_SQL_QUERY)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                contains = true;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return contains;
    }

    @Override
    public Optional<User> findUser(String login, String password) throws DaoException {
        Optional<User> optionalUser;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_SQL_QUERY)) {
            statement.setString(1, login);
            ResultSet userData = statement.executeQuery();
            if (userData.next()) {
                String hashedPassword = userData.getNString(2);
                if (Encryptor.coincide(password, hashedPassword)) {
                    int userId = userData.getInt(1);
                    String name = userData.getNString(3);
                    String surname = userData.getNString(4);
                    String email = userData.getNString(5);
                    String role = userData.getNString(6);
                    UserRole userRole = UserRole.valueOf(role);
                    User user = new User(userId, login, name, surname, email, userRole);
                    String photoName = userData.getNString(7);
                    if (photoName != null) {
                        user.setPhotoName(photoName);
                    }
                    optionalUser = Optional.of(user);
                } else {
                    logger.error("Incorrect password inputted for login {}", login);
                    optionalUser = Optional.empty();
                }
            } else {
                optionalUser = Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return optionalUser;
    }

    @Override
    public Optional<String> findUserEmail(int userId) throws DaoException {
        Optional<String> optEmail;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_EMAIL_SQL_QUERY)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getNString(1);
                optEmail = Optional.of(email);
            } else {
                optEmail = Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return optEmail;
    }

    @Override
    public boolean addUser(User user, String hashedPassword) throws DaoException {
        boolean addedSuccessfully;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_USER_SQL_QUERY)) {
            String login = user.getLogin();
            String name = user.getName();
            String surname = user.getSurname();
            String email = user.getEmail();
            UserRole userRole = user.getRole();
            statement.setString(1, login);
            statement.setString(2, hashedPassword);
            statement.setString(3, name);
            statement.setString(4, surname);
            statement.setString(5, email);
            statement.setString(6, userRole.toString());
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                addedSuccessfully = true;
                logger.info("User added successfully");
            } else {
                logger.error("User {} wasn't added", user);
                addedSuccessfully = false;
            }
        } catch (SQLException e) {
            logger.error("Exception while adding new user", e);
            throw new DaoException("Exception while adding new user", e);
        }
        return addedSuccessfully;
    }

    @Override
    public boolean updateUserPhoto(String login, String photoName) throws DaoException {
        boolean updatedSuccessfully;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PHOTO_SQL_QUERY)) {
            statement.setString(1, photoName);
            statement.setString(2, login);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                updatedSuccessfully = true;
                logger.info("User's photo name updated successfully");
            } else {
                logger.error("Photo name of user {} wasn't updated", login);
                updatedSuccessfully = false;
            }
        } catch (SQLException e) {
            logger.error("Exception while updating user's photo name", e);
            throw new DaoException("Exception while updating user's photo name", e);
        }
        return updatedSuccessfully;
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> userList;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_SQL_QUERY);
            userList = completeList(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return userList;
    }

    private List<User> completeList(ResultSet users) throws SQLException {
        List<User> userList = new ArrayList<>();
        while(users.next()) {
            int userId = users.getInt(1);
            String login = users.getString(2);
            String name = users.getString(3);
            String surname = users.getString(4);
            String email = users.getString(5);
            UserRole role = UserRole.valueOf(users.getString(6));
            boolean isBlocked = users.getBoolean(7);
            LocalDateTime registeredAt = (LocalDateTime) users.getObject(8);
            User user = new User(userId, login, name, surname, email, role, registeredAt, isBlocked);
            userList.add(user);
        }
        return userList;
    }

    @Override
    public Optional<User> find(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public User update(User entity) {
        return null;
    }
}
