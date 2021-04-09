package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.models.*;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataAccessor {

    private static DataAccessor da = new DataAccessor("jdbc:postgresql://localhost:5432/postgres","postgres","2611");
    private Connection connection;

    public static DataAccessor getDataAccessor(){
        return da;
    }

    private DataAccessor(String url, String usr, String pass){
        try{
            connection = DriverManager.getConnection(url, usr, pass);
        } catch (SQLException e){

        }
    }

    public ObservableList<Organ> getOrgansList() {

        ObservableList<Organ> organs = FXCollections.observableArrayList();
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT organ_id, organ_name, t.type_name as ty_na, d.donor_l_name as do_na, s.surgeon_l_name as su_na, organ_specification, organ_price FROM organs\n" +
                    "    left join types t on t.type_id = organs.organ_type\n" +
                    "    left join donors d on organs.organ_donor = d.donor_id\n" +
                    "    left join surgeons s on organs.organ_surgeon = s.surgeon_id;");


            while(rs.next()){
                organs.add(
                        new Organ(rs.getInt("organ_id"),
                                rs.getString("organ_name"),
                                String.valueOf(rs.getString("ty_na")),
                                rs.getString("do_na"),
                                rs.getString("su_na"),
                                rs.getString("organ_specification"),
                                rs.getInt("organ_price")));
            }

            return organs;
        } catch (SQLException e){
            System.out.println("pipiska");
        }
        return organs;
    }

    public ObservableList<Surgeon> getSurgeonsList() {

        ObservableList<Surgeon> surgeons = FXCollections.observableArrayList();
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM surgeons;");


            while(rs.next()){
                surgeons.add(
                        new Surgeon(rs.getInt("surgeon_id"),
                                rs.getString("surgeon_f_name"),
                                rs.getString("surgeon_l_name"),
                                rs.getInt("surgeon_exp"),
                                rs.getString("surgeon_email"),
                                rs.getString("surgeon_password")));
            }

            return surgeons;
        } catch (SQLException e){
            System.out.println("pipiska");
        }
        return surgeons;
    }

    public ObservableList<Order> getOrdersList() {

        ObservableList<Order> orders = FXCollections.observableArrayList();
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT payment_id, n.node_code AS node, a.admin_l_name AS ad_lname, a.admin_f_name AS ad_fname, b.buyer_l_name AS b_lname, b.buyer_f_name AS b_fname, payment_price, pm.method_name AS pm_name FROM payments\n" +
                    "    left join admins a on payments.payment_admin = a.admin_id\n" +
                    "    left join buyers b on payments.payment_buyer = b.buyer_id\n" +
                    "    left JOIN nodes n on payments.payment_node = n.node_id\n" +
                    "    left join p_methods pm on payments.payment_method = pm.method_id;");


            while(rs.next()){
                orders.add(
                        new Order(rs.getInt("payment_id"),
                                rs.getString("node"),
                                rs.getString("ad_lname") + " " + rs.getString("ad_fname"),
                                rs.getString("b_lname") + " " + rs.getString("b_fname"),
                                rs.getInt("payment_price"),
                                rs.getString("pm_name")));
            }

            return orders;
        } catch (SQLException e){
            System.out.println("pipiska");
        }
        return orders;
    }

    public Administrator getAdministratorByPassword(String email, String password){
        Administrator admin = new Administrator();
        admin.setId(-1);

        try(PreparedStatement statement = connection.prepareStatement("SELECT admin_id, admin_f_name, admin_l_name, admin_email, admin_phone, admin_password FROM admins WHERE admin_email = (?) AND admin_password = (?)")){
            statement.setString(1, email);
            statement.setString(2, password);
            final ResultSet rs = statement.executeQuery();
            if(rs.next()){
                admin.setId(rs.getInt("admin_id"));
                admin.setL_name(rs.getString("admin_l_name"));
                admin.setF_name(rs.getString("admin_f_name"));
                admin.setEmail(rs.getString("admin_email"));
                admin.setPhone(rs.getString("admin_phone"));
                admin.setPassword(rs.getString("admin_password"));
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return admin;
    }

    public Surgeon getSurgeonByPassword(String email, String password){
        Surgeon surgeon = new Surgeon();
        surgeon.setId(-1);

        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM surgeons WHERE surgeon_email = (?) AND surgeon_password = (?)")){
            statement.setString(1, email);
            statement.setString(2, password);
            final ResultSet rs = statement.executeQuery();
            if(rs.next()){
                surgeon.setId(rs.getInt("surgeon_id"));
                surgeon.setL_name(rs.getString("surgeon_l_name"));
                surgeon.setF_name(rs.getString("surgeon_f_name"));
                surgeon.setEmail(rs.getString("surgeon_email"));
                surgeon.setExp(rs.getInt("surgeon_exp"));
                surgeon.setPassword(rs.getString("surgeon_password"));
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return surgeon;
    }

    public ObservableList<String> getTypesList(){
        ObservableList<String> types = FXCollections.observableArrayList();
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM types;");


            while(rs.next()){
                types.add(rs.getString("type_name"));
            }

            return types;
        } catch (SQLException e){
            System.out.println("pipiska");
        }

        return types;
    }

    public ObservableList<String> getDonorsList(){
        ObservableList<String> donors = FXCollections.observableArrayList();
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM donors;");


            while(rs.next()){
                donors.add(rs.getString("donor_l_name") + " " + rs.getString("donor_f_name"));
            }

            return donors;
        } catch (SQLException e){
            System.out.println("pipiska");
        }

        return donors;
    }

    public int getSurgeonIdByName(String fullName){
        int sId = -1;
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM surgeons WHERE surgeon_l_name = (?) AND surgeon_f_name = (?)")){
            statement.setString(1, fullName.split(" ")[0]);
            statement.setString(2, fullName.split(" ")[1]);
            final ResultSet rs = statement.executeQuery();
            if(rs.next()){
                sId = rs.getInt("surgeon_id");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return sId;
    }

    public Surgeon getSurgeonByName(String lName, String fName){
        Surgeon surgeon = new Surgeon();
        surgeon.setId(-1);

        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM surgeons WHERE surgeon_l_name = (?) AND surgeon_f_name = (?)")){
            statement.setString(1, lName);
            statement.setString(2, fName);
            final ResultSet rs = statement.executeQuery();
            if(rs.next()){
                surgeon.setId(rs.getInt("surgeon_id"));
                surgeon.setL_name(rs.getString("surgeon_l_name"));
                surgeon.setF_name(rs.getString("surgeon_f_name"));
                surgeon.setExp(rs.getInt("surgeon_exp"));
                surgeon.setEmail(rs.getString("surgeon_email"));
                surgeon.setPassword(rs.getString("surgeon_password"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return surgeon;
    }

    public Surgeon getSurgeonById(int id){
        Surgeon surgeon = new Surgeon();
        surgeon.setId(-1);

        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM surgeons WHERE surgeon_id = (?)")){
            statement.setInt(1, id);
            final ResultSet rs = statement.executeQuery();
            if(rs.next()){
                surgeon.setId(rs.getInt("surgeon_id"));
                surgeon.setL_name(rs.getString("surgeon_l_name"));
                surgeon.setF_name(rs.getString("surgeon_f_name"));
                surgeon.setExp(rs.getInt("surgeon_exp"));
                surgeon.setEmail(rs.getString("surgeon_email"));
                surgeon.setPassword(rs.getString("surgeon_password"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return surgeon;
    }

    public Organ getOrganById(int id){
        Organ organ = new Organ();
        organ.setId(-1);

        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM organs WHERE organ_id = (?)")){
            statement.setInt(1, id);
            final ResultSet rs = statement.executeQuery();
            if(rs.next()){
                organ.setId(rs.getInt("organ_id"));
                organ.setName(rs.getString("organ_name"));
                organ.setSpecification(rs.getString("organ_specification"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return organ;
    }

    public Organ getOrganByNameAndDonor(String name, int donor){
        Organ organ = new Organ();
        organ.setId(-1);
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM organs WHERE organ_name = (?) AND organ_donor = (?)")){
            statement.setString(1, name);
            statement.setInt(2, donor);
            final ResultSet rs = statement.executeQuery();
            if(rs.next()){
                organ.setId(rs.getInt("organ_id"));
                organ.setName(rs.getString("organ_name"));
                organ.setSpecification(rs.getString("organ_specification"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return organ;

    }

    public Order getOrderByCode(String code){
        Order order = new Order();
        order.setId(-1);

        try(PreparedStatement statement = connection.prepareStatement("SELECT payment_id, n.node_code AS node, a.admin_l_name AS ad_lname, a.admin_f_name AS ad_fname, b.buyer_l_name AS b_lname, b.buyer_f_name AS b_fname, payment_price, pm.method_name AS pm_name FROM payments\n" +
                "    left join admins a on payments.payment_admin = a.admin_id\n" +
                "    left join buyers b on payments.payment_buyer = b.buyer_id\n" +
                "    left JOIN nodes n on payments.payment_node = n.node_id\n" +
                "    left join p_methods pm on payments.payment_method = pm.method_id WHERE n.node_code = (?)")){
            statement.setString(1, code);
            final ResultSet rs = statement.executeQuery();
            if(rs.next()){
                order.setId(rs.getInt("payment_id"));
                order.setNode(rs.getString("node"));
                order.setAdmin(rs.getString("ad_lname") + " " + rs.getString("ad_fname"));
                order.setBuyer(rs.getString("b_lname") + " " + rs.getString("b_fname"));
                order.setPrice(rs.getInt("payment_price"));
                order.setMethod(rs.getString("pm_name"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return order;
    }

    public boolean createNewOrgan(String name, int type, int donor, int surgeon, String specification, int price){
        boolean result = false;

        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO organs (organ_name, organ_type, organ_donor, organ_surgeon, organ_specification, organ_price) VALUES ((?), (?), (?), (?), (?), (?))")){
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, type);
            preparedStatement.setInt(3, donor);
            preparedStatement.setInt(4, surgeon);
            preparedStatement.setString(5, specification);
            preparedStatement.setInt(6, price);
            result = preparedStatement.executeQuery().next();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean createNewSurgeon(String fname, String lname, int exp, String email, String password){
        boolean result = false;

        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO surgeons (surgeon_f_name, surgeon_l_name, surgeon_exp, surgeon_email, surgeon_password) VALUES ((?), (?), (?), (?), (?))")){
            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, lname);
            preparedStatement.setInt(3, exp);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, password);
            result = preparedStatement.executeQuery().next();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean createNewNode(String code, Date date, int surgeonId, int organId, int nodePrice){
        boolean result = false;

        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO nodes (node_code, node_date, node_surgeon, node_organ, node_price) VALUES ((?), (?), (?), (?), (?))")){
            preparedStatement.setString(1, code);

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = dateFormat.format(date);
            preparedStatement.setDate(2, java.sql.Date.valueOf(dateStr));
            preparedStatement.setInt(3, surgeonId);
            preparedStatement.setInt(4, organId);
            preparedStatement.setInt(5, nodePrice);
            result = preparedStatement.executeQuery().next();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean changeSurgeon(int id, String fname, String lname, int exp, String email, String password){
        boolean result = false;

        try(PreparedStatement preparedStatement = connection.prepareStatement("UPDATE surgeons SET surgeon_f_name = (?), surgeon_l_name = (?), surgeon_exp = (?), surgeon_email = (?), surgeon_password = (?) WHERE surgeon_id = (?) RETURNING surgeon_id")){
            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, lname);
            preparedStatement.setInt(3, exp);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, password);
            preparedStatement.setInt(6, id);
            result = preparedStatement.executeQuery().next();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean deleteOrganById(int id){
        boolean result = false;
        try(PreparedStatement statement = connection.prepareStatement("DELETE FROM organs WHERE organ_id = (?) RETURNING organ_id")){
            statement.setInt(1, id);
            result = statement.executeQuery().next();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Успешно удален орган!");
        return result;
    }

    public boolean deleteNodeByOrganId(int id){
        boolean result = false;
        try(PreparedStatement statement = connection.prepareStatement("DELETE FROM nodew WHERE node_organ = (?) RETURNING node_organ")){
            statement.setInt(1, id);
            result = statement.executeQuery().next();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Успешно удален документ!");
        return result;
    }

    public boolean deleteSurgeonById(int id){
        boolean result = false;
        try(PreparedStatement statement = connection.prepareStatement("DELETE FROM surgeons WHERE surgeon_id = (?) RETURNING surgeon_id")){
            statement.setInt(1, id);
            result = statement.executeQuery().next();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public OrderNode getNodeByCode(String code){
        OrderNode node = new OrderNode();
        node.setId(-1);

        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM nodes WHERE node_code = (?)")){
            statement.setString(1, code);
            final ResultSet rs = statement.executeQuery();
            if(rs.next()){
                node.setId(rs.getInt("node_id"));
                node.setCode(rs.getString("node_code"));
                node.setDate(rs.getDate("node_date"));
                node.setOrganId(rs.getInt("node_organ"));
                node.setSurgeonId(rs.getInt("node_surgeon"));
                node.setPrice(rs.getInt("node_price"));
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return node;
    }

    public ObservableList<String> getNodesCodesList() {
        ObservableList<String> nodes = FXCollections.observableArrayList();
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM nodes;");
            while(rs.next()){
                nodes.add(rs.getString("node_code"));
            }
            return nodes;
        } catch (SQLException e){
            System.out.println("pipiska");
        }
        return nodes;
    }

    public ObservableList<String> getMethodsNamesList() {
        ObservableList<String> methods = FXCollections.observableArrayList();
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM p_methods;");
            while(rs.next()){
                methods.add(rs.getString("method_name"));
            }
            return methods;
        } catch (SQLException e){
            System.out.println("pipiska");
        }
        return methods;
    }

    public ObservableList<String> getBuyersNamesList() {
        ObservableList<String> buyers = FXCollections.observableArrayList();
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM buyers;");
            while(rs.next()){
                buyers.add(rs.getString("buyer_l_name") + " " + rs.getString("buyer_f_name"));
            }
            return buyers;
        } catch (SQLException e){
            System.out.println("pipiska");
        }
        return buyers;
    }

    public int getAdministratorIdByName(String fullName) {
        int sId = -1;
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM admins WHERE admin_l_name = (?) AND admin_f_name = (?)")){
            statement.setString(1, fullName.split(" ")[0]);
            statement.setString(2, fullName.split(" ")[1]);
            final ResultSet rs = statement.executeQuery();
            if(rs.next()){
                sId = rs.getInt("admin_id");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return sId;

    }

    public boolean createNewOrder(int nodeId, int adminId, int buyerId, int orderPrice, int methodId) {

        boolean result = false;

        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO payments (payment_node, payment_admin, payment_buyer, payment_price, payment_method) VALUES ((?), (?), (?), (?), (?))")){
            preparedStatement.setInt(1, nodeId);
            preparedStatement.setInt(2, adminId);
            preparedStatement.setInt(3, buyerId);
            preparedStatement.setInt(4, orderPrice);
            preparedStatement.setInt(5, methodId);
            result = preparedStatement.executeQuery().next();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean createNewBuyer(String fName, String lName, String email) {

        boolean result = false;

        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO buyers (buyer_f_name, buyer_l_name, buyer_email) VALUES ((?), (?), (?))")){
            preparedStatement.setString(1, fName);
            preparedStatement.setString(2, lName);
            preparedStatement.setString(3, email);
            result = preparedStatement.executeQuery().next();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public int getBuyerIdByFullNameAndEmail(String fName, String lName, String email) {
        int sId = -1;
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM buyers WHERE buyer_f_name = (?) AND buyer_l_name = (?) AND buyer_email = (?)")){
            statement.setString(1, fName);
            statement.setString(2, lName);
            statement.setString(3, email);
            final ResultSet rs = statement.executeQuery();
            if(rs.next()){
                sId = rs.getInt("buyer_id");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return sId;
    }
}
