package com.example.menu.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Parameter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ToService {
    private String url="jdbc:h2:file:./data/recipe_db";
    String username = "sa";
    String password = "";
    @Autowired
    private EmailService emailService;
    public Boolean login(String account,String password1,String option){
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
            String sql="SELECT * FROM ACCOUNT WHERE ACCOUNT=? AND PASSWORD=? AND OPTION=?";
            PreparedStatement ptmt=conn.prepareStatement(sql);
            ptmt.setString(1, account);
            ptmt.setString(2, password1);
            ptmt.setString(3, option);
            ResultSet rs=ptmt.executeQuery();
            if(rs.next()){
                return true;
            }
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Map<String, Object>> search(String searchKey, String category) {
        List<Map<String, Object>> list = new ArrayList<>();
        String sql;

        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);

            if (searchKey == null || searchKey.isEmpty()) {
                if (category.equals("全部")) {
                    sql = "SELECT * FROM RECIPE";
                    PreparedStatement ptmt = conn.prepareStatement(sql);
                    ResultSet rs = ptmt.executeQuery();
                    while (rs.next()) {
                        Map<String, Object> result = new HashMap<>();
                        result.put("id", rs.getInt("ID"));
                        result.put("name", rs.getString("NAME"));
                        result.put("category", rs.getString("CATEGORY"));
                        result.put("ingredients", rs.getString("INGREDIENTS"));
                        result.put("steps", rs.getString("STEPS"));
                        list.add(result);
                    }
                } else {
                    sql = "SELECT * FROM RECIPE WHERE CATEGORY = ?";
                    PreparedStatement ptmt = conn.prepareStatement(sql);
                    ptmt.setString(1, category);
                    ResultSet rs = ptmt.executeQuery();
                    while (rs.next()) {
                        Map<String, Object> result = new HashMap<>();
                        result.put("id", rs.getInt("ID"));
                        result.put("name", rs.getString("NAME"));
                        result.put("category", rs.getString("CATEGORY"));
                        result.put("ingredients", rs.getString("INGREDIENTS"));
                        result.put("steps", rs.getString("STEPS"));
                        list.add(result);
                    }
                }
            } else {
                if (category.equals("全部")) {
                    sql = "SELECT * FROM RECIPE WHERE NAME LIKE ?";
                    PreparedStatement ptmt = conn.prepareStatement(sql);
                    ptmt.setString(1, "%" + searchKey + "%");
                    ResultSet rs = ptmt.executeQuery();
                    while (rs.next()) {
                        Map<String, Object> result = new HashMap<>();
                        result.put("id", rs.getInt("ID"));
                        result.put("name", rs.getString("NAME"));
                        result.put("category", rs.getString("CATEGORY"));
                        result.put("ingredients", rs.getString("INGREDIENTS"));
                        result.put("steps", rs.getString("STEPS"));
                        list.add(result);
                    }
                } else {
                    sql = "SELECT * FROM RECIPE WHERE NAME LIKE ? AND CATEGORY = ?";
                    PreparedStatement ptmt = conn.prepareStatement(sql);
                    ptmt.setString(1, "%" + searchKey + "%");
                    ptmt.setString(2, category);
                    ResultSet rs = ptmt.executeQuery();
                    while (rs.next()) {
                        Map<String, Object> result = new HashMap<>();
                        result.put("id", rs.getInt("ID"));
                        result.put("name", rs.getString("NAME"));
                        result.put("category", rs.getString("CATEGORY"));
                        result.put("ingredients", rs.getString("INGREDIENTS"));
                        result.put("steps", rs.getString("STEPS"));
                        list.add(result);
                    }
                }
            }
            conn.close();
            return list;

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addRecipe(String name, String category, String ingredients, String steps) {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
            String sql = "INSERT INTO RECIPE (NAME, CATEGORY, INGREDIENTS, STEPS) VALUES (?, ?, ?, ?)";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, name);
            ptmt.setString(2, category);
            ptmt.setString(3, ingredients);
            ptmt.setString(4, steps);
            ptmt.executeUpdate();
            conn.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteRecipe(int id) {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
            String sql = "DELETE FROM RECIPE WHERE ID = ?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ptmt.executeUpdate();
            conn.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateRecipe(int id, String name, String category, String ingredients, String steps) {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
            String sql = "UPDATE RECIPE SET NAME = ?, CATEGORY = ?, INGREDIENTS = ?, STEPS = ? WHERE ID = ?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, name);
            ptmt.setString(2, category);
            ptmt.setString(3, ingredients);
            ptmt.setString(4, steps);
            ptmt.setInt(5, id);
            ptmt.executeUpdate();
            conn.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Map<String, Object>> searchCard() {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
            String sql = "SELECT * FROM CARD";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> result = new HashMap<>();
                result.put("id", rs.getInt("ID"));
                result.put("name", rs.getString("NAME"));
                result.put("intro", rs.getString("INTRO"));
                result.put("cost", rs.getString("COST"));
                list.add(result);
            }
            conn.close();
            return list;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addCard(String name, String intro, String cost) {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
            String sql = "INSERT INTO CARD (NAME, INTRO, COST) VALUES (?, ?, ?)";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, name);
            ptmt.setString(2, intro);
            ptmt.setString(3, cost);
            ptmt.executeUpdate();
            conn.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void editCard(int id, String name, String intro, String cost) {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
            String sql = "UPDATE CARD SET NAME = ?, INTRO = ?, COST = ? WHERE ID = ?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, name);
            ptmt.setString(2, intro);
            ptmt.setString(3, cost);
            ptmt.setInt(4, id);
            ptmt.executeUpdate();
            conn.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteCard(int id) {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
            String sql = "DELETE FROM CARD WHERE ID = ?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ptmt.executeUpdate();
            conn.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Map<String, Object>> SearchUser() {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
            String sql = "SELECT ACCOUNT, COST FROM ACCOUNT WHERE OPTION = ?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, "1");
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> result = new HashMap<>();
                result.put("account", rs.getString("ACCOUNT"));
                result.put("cost", rs.getInt("COST"));
                list.add(result);
            }
            conn.close();
            return list;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateUserCost(String account, int cost) {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
            String sql = "UPDATE ACCOUNT SET COST = ? WHERE ACCOUNT = ?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, cost);
            ptmt.setString(2, account);
            ptmt.executeUpdate();
            conn.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Integer getUserCost() {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
            String sql = "SELECT COST FROM ACCOUNT WHERE ACCOUNT = 'wty'";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("COST");
            }
            return 0;

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Map<String, Object>> getTasks() {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
            String sql = "SELECT ID, NAME, REWARD, TASK_DATE FROM TASK";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();

            // 获取今天的日期字符串
            String today = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());

            while (rs.next()) {
                Map<String, Object> result = new HashMap<>();
                result.put("id", rs.getInt("ID"));
                result.put("name", rs.getString("NAME"));
                result.put("reward", rs.getInt("REWARD"));

                // 直接比较字符串
                String taskDate = rs.getString("TASK_DATE");
                result.put("status", today.equals(taskDate) ? "T" : "F");

                list.add(result);
            }
            conn.close();
            return list;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void completeTask(int taskId) {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);

            // 1. 获取任务奖励
            String rewardSql = "SELECT REWARD FROM TASK WHERE ID = ?";
            PreparedStatement rewardStmt = conn.prepareStatement(rewardSql);
            rewardStmt.setInt(1, taskId);
            ResultSet rs = rewardStmt.executeQuery();
            rs.next();
            int reward = rs.getInt("REWARD");
            rs.close();
            rewardStmt.close();

            // 2. 更新 TASK 的日期为今天
            String updateTaskSql = "UPDATE TASK SET TASK_DATE = CURRENT_DATE WHERE ID = ?";
            PreparedStatement updateTaskStmt = conn.prepareStatement(updateTaskSql);
            updateTaskStmt.setInt(1, taskId);
            updateTaskStmt.executeUpdate();
            updateTaskStmt.close();

            // 3. 更新 ACCOUNT 的 cost（wty）
            String updateSql = "UPDATE ACCOUNT SET COST = COST + ? WHERE ACCOUNT = 'wty'";
            PreparedStatement updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setInt(1, reward);
            updateStmt.executeUpdate();
            updateStmt.close();

            conn.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void buyCard(int cardId) {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);

            // 1. 获取卡片价格
            String priceSql = "SELECT COST FROM CARD WHERE ID = ?";
            PreparedStatement priceStmt = conn.prepareStatement(priceSql);
            priceStmt.setInt(1, cardId);
            ResultSet rs = priceStmt.executeQuery();
            rs.next();
            int price = rs.getInt("COST");
            rs.close();
            priceStmt.close();

            // 2. 扣减余额（wty）
            String updateSql = "UPDATE ACCOUNT SET COST = COST - ? WHERE ACCOUNT = 'wty' AND COST >= ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setInt(1, price);
            updateStmt.setInt(2, price);
            updateStmt.executeUpdate();
            updateStmt.close();

            // 3. 插入购买记录
            String insertSql = "INSERT INTO USER_CARD (CARD_ID) VALUES (?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
            insertStmt.setInt(1, cardId);
            insertStmt.executeUpdate();
            insertStmt.close();

            conn.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Map<String, Object>> getUserCards() {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
            String sql = "SELECT uc.ID, uc.STATUS, uc.BUY_DATE, uc.USE_DATE, c.NAME " +
                    "FROM USER_CARD uc JOIN CARD c ON uc.CARD_ID = c.ID ORDER BY uc.BUY_DATE DESC";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> item = new HashMap<>();
                item.put("id", rs.getInt("ID"));
                item.put("name", rs.getString("NAME"));
                item.put("status", rs.getInt("STATUS"));
                item.put("buyDate", rs.getString("BUY_DATE"));
                item.put("useDate", rs.getString("USE_DATE"));
                list.add(item);
            }
            conn.close();
            return list;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String doLottery() {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);

            // 1. 扣减5币
            String updateSql = "UPDATE ACCOUNT SET COST = COST - 5 WHERE ACCOUNT = 'wty'";
            PreparedStatement updateStmt = conn.prepareStatement(updateSql);
            updateStmt.executeUpdate();
            updateStmt.close();

            // 2. 抽奖
            int rand = (int) (Math.random() * 100) + 1;
            String prizeName = "";
            int prizeValue = 0;
            String cardName = null;

            if (rand <= 16) {
                prizeName = "1币";
                prizeValue = 1;
            } else if (rand <= 30) {
                prizeName = "2币";
                prizeValue = 2;
            } else if (rand <= 42) {
                prizeName = "3币";
                prizeValue = 3;
            } else if (rand <= 52) {
                prizeName = "5币";
                prizeValue = 5;
            } else if (rand <= 60) {
                prizeName = "8币";
                prizeValue = 8;
            } else if (rand <= 67) {
                prizeName = "10币";
                prizeValue = 10;
            } else if (rand <= 73) {
                prizeName = "20币";
                prizeValue = 20;
            } else if (rand <= 79) {
                prizeName = "奶茶卡";
                prizeValue = 0;
                cardName = "奶茶卡";
            } else if (rand <= 84) {
                prizeName = "外卖卡";
                prizeValue = 0;
                cardName = "外卖卡";
            } else if (rand <= 88) {
                prizeName = "零食卡";
                prizeValue = 0;
                cardName = "零食卡";
            } else if (rand <= 92) {
                prizeName = "鲜花卡";
                prizeValue = 0;
                cardName = "鲜花卡";
            } else if (rand <= 95) {
                prizeName = "早餐卡";
                prizeValue = 0;
                cardName = "早餐卡";
            } else if (rand <= 98) {
                prizeName = "50币";
                prizeValue = 50;
            } else {
                prizeName = "任何要求卡";
                prizeValue = 0;
                cardName = "任何要求卡";
            }

            // 3. 发放奖励
            if (prizeValue > 0) {
                String addSql = "UPDATE ACCOUNT SET COST = COST + ? WHERE ACCOUNT = 'wty'";
                PreparedStatement addStmt = conn.prepareStatement(addSql);
                addStmt.setInt(1, prizeValue);
                addStmt.executeUpdate();
                addStmt.close();
            } else if (cardName != null) {
                String cardSql = "SELECT ID FROM CARD WHERE NAME = ?";
                PreparedStatement cardStmt = conn.prepareStatement(cardSql);
                cardStmt.setString(1, cardName);
                ResultSet cardRs = cardStmt.executeQuery();
                if (cardRs.next()) {
                    int cardId = cardRs.getInt("ID");
                    String insertSql = "INSERT INTO USER_CARD (CARD_ID) VALUES (?)";
                    PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                    insertStmt.setInt(1, cardId);
                    insertStmt.executeUpdate();
                    insertStmt.close();
                }
                cardRs.close();
                cardStmt.close();
            }

            conn.close();
            return prizeName;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void giveCardToUser(int cardId) {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
            String insertSql = "INSERT INTO USER_CARD (CARD_ID) VALUES (?)";
            PreparedStatement ptmt = conn.prepareStatement(insertSql);
            ptmt.setInt(1, cardId);
            ptmt.executeUpdate();
            conn.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void useCard(int id) {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);

            // 先获取卡片名称
            String getNameSql = "SELECT c.NAME FROM USER_CARD uc JOIN CARD c ON uc.CARD_ID = c.ID WHERE uc.ID = ?";
            PreparedStatement getNameStmt = conn.prepareStatement(getNameSql);
            getNameStmt.setInt(1, id);
            ResultSet rs = getNameStmt.executeQuery();
            String cardName = "";
            if (rs.next()) {
                cardName = rs.getString("NAME");
            }
            rs.close();
            getNameStmt.close();

            // 更新卡片状态为已使用
            String updateSql = "UPDATE USER_CARD SET STATUS = 1, USE_DATE = CURRENT_TIMESTAMP WHERE ID = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setInt(1, id);
            updateStmt.executeUpdate();
            updateStmt.close();

            conn.close();

            // 发送邮件
            if (!cardName.isEmpty()) {
                emailService.sendCardNotification(cardName);
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

