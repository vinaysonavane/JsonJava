import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class JsonToJava {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        ArrayList<CustomerDetails> al = new ArrayList<CustomerDetails>();
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business","root","root");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from CustomerInfo where PurchasedDate = curdate() and Location = 'Asia';");
        while (rs.next())
        {
            CustomerDetails cd = new CustomerDetails();
            cd.setCourseName(rs.getString(1));
            cd.setPurchasedDate(rs.getString(2));
            cd.setAmount(rs.getInt(3));
            cd.setLocation(rs.getString(4));
            al.add(cd);
        }

       // for (int i=0; i<al.size(); i++ )
        //{
          //  om.writeValue(new File("C:\\Users\\vinay\\IdeaProjects\\JsonJava\\customerInfo"+i+".json"),al.get(i));
        //}
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File("C:\\Users\\vinay\\IdeaProjects\\JsonJava\\customerInfo.json"), al);


        conn.close();



    }
}
