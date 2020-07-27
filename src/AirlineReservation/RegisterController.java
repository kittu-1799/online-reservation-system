package AirlineReservation;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class RegisterController {

    @FXML
    public TextField name,fno,phoneno,seats,email;

    public boolean check_pno(ResultSet rss)throws Exception{
        if(rss.next())
        {
            return true;
        }
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Flight not found");
        alert.show();
        return false;
    }

    public boolean check_pno1(ResultSet rss)throws Exception{
        if(rss.next())
        {
            return true;
        }
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Email not registered");
        alert.show();
        return false;
    }
    public void save_data()throws Exception{


        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://localhost:3306/airline?autoReconnect=true&useSSL=false";
        Connection con = DriverManager.getConnection(url, "root", "0209");
        System.out.println("Connected to DB");
        Statement st=con.createStatement();
        ResultSet rs;


        String fno_i=fno.getText();

        String name_i=name.getText();
        String ano_i=phoneno.getText();
        String seats_i=seats.getText();
        String phone_i=email.getText();
        Integer cid = new Integer(0);
        Integer lno_i = new Integer(0);
        Integer price = new Integer(0);

        if(fno_i.compareTo("")==0 || name_i.compareTo("")==0 || ano_i.compareTo("")==0 || seats_i.compareTo("")==0 || phone_i.compareTo("")==0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Some Field is empty");
            alert.show();
            return;

        }
        int fno=Integer.parseInt(fno_i);
        int ano=Integer.parseInt(ano_i);
        int seat=Integer.parseInt(seats_i);
        if(check_pno(st.executeQuery("select fno from flights where exists(select fno from flights where fno="+fno+");")))
        {
            String q="select max(rno) as cid from registered;";
            rs=st.executeQuery(q);
            while (rs.next()) {
                System.out.println(rs.getInt("cid"));
                cid=rs.getInt("cid");
            }
            cid++;


            if(check_pno1(st.executeQuery("select lno from login where email='"+phone_i+"';")))
            {
                String q1="select lno as lno from login where email='"+phone_i+"';";
                rs = st.executeQuery(q1);
                while (rs.next()) {
                    System.out.println(rs.getInt("lno"));
                    lno_i = rs.getInt("lno");
                }

                String q2="select price as price from flights where fno="+fno+";";
                rs = st.executeQuery(q2);
                while (rs.next()) {
                    System.out.println(rs.getInt("price"));
                    price = rs.getInt("price");
                }
                price=price*seat;

                String query_input="insert into registered values("+cid+","+lno_i+","+fno+","+ano+");";
                String Query="insert into customer values("+cid+",\""+name_i+"\","+price+","+seat+");";
                try{
                    System.out.println(query_input);
                    st.executeUpdate(query_input);
                    System.out.println(Query);
                    st.executeUpdate(Query);
                    System.out.println("done");

                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Data Saved");
                    alert.show();
                    System.exit(0);
                }
                catch (Exception eq)
                {
                    System.out.println("Error");
                }
            }


        }
    }
}

