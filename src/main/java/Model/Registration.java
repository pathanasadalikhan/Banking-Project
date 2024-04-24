package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import jakarta.servlet.http.HttpSession;

public class Registration {
	private Connection con;
	HttpSession se;
	public Registration(HttpSession session) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&&password=Asad@007");
			se=session;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public String Registration(String name, String phone, String email, String pw) {
		PreparedStatement ps;
		String status="";
		String sql="select * from sookshma.sookshmas1 where phone='" + phone + "' or email='" + email + "';";
		try {
            Statement st = null;
            ResultSet rs = null;
            st = con.createStatement();
            rs = st.executeQuery(sql);
            boolean b=rs.next();
            if(b) {
            	status="existed";
            }
            else {
            	ps=(PreparedStatement)con.prepareStatement("insert into sookshma.sookshmas1(name,phone,email,pw,balance) values(?,?,?,?,?)");//"insert into sookshma.Banking values('"+id,email,name,amt,0,bal+"')"
                ps.setString(1, name);
                ps.setString(2, phone);
                ps.setString(3, email);
                ps.setString(4, pw);
                ps.setLong(5, 0);
                int a = ps.executeUpdate();
                if (a > 0) {	
                    status = "success";
                }else {
                	status="failure";
                }
            }
            
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	public String login(String email, String pass) {
        String status1 = ""; 
        long id,balance;
        String name = "", emails = "";

        try {
            PreparedStatement pstm=null;
            ResultSet rs = null;
            String sql="select* from sookshma.sookshmas1 where email=? and pw=?";
           pstm=con.prepareStatement(sql);
           pstm.setString(1, email);
           pstm.setString(2, pass);
           rs=pstm.executeQuery();
            boolean b = rs.next();
            if (b == true) {
                id = rs.getLong("slno");
                name = rs.getString("name");
                emails = rs.getString("email");
                balance =rs.getLong("balance");
                se.setAttribute("uname", name);
                se.setAttribute("email", emails);
                se.setAttribute("id", id);
                se.setAttribute("balance", balance);
                status1 = "success";
            } else {
                status1 = "failure";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status1;
    }
	public Student getInfo() {
        Statement st = null;
        ResultSet rs = null;
        Student s = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from sookshma.sookshmas1 where slno= '" + se.getAttribute("id") + "'");
            boolean b = rs.next();
            if (b == true) {
                s = new Student();
                s.setName(rs.getString("name"));
                s.setphone(rs.getString("phone"));
                s.setemail(rs.getString("email"));
            } else {
                s = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
    }
	public String update(String name, String pno, String email) {
        String status = "";
        Statement st = null;
        try {
            st = con.createStatement();
            st.executeUpdate("update sookshma.sookshmas1 set name='" + name + "',phone='" + pno + "',email='" + email + "' where slno= '" + se.getAttribute("id") + "' ");
            se.setAttribute("uname", name);
            se.setAttribute("email",email);
            status = "success";
        } catch (Exception e) {
            status = "failure";
            e.printStackTrace();
        }

        return status;
    }
	public ArrayList<Student> getUserinfo(Long id) {
        Statement st = null;
        ResultSet rs = null;
        ArrayList<Student> al = new ArrayList<Student>();
        try {
            st = con.createStatement();
            String qry = "select * from sookshma.sookshmas1 where slno = '" + id + "';";
            rs = st.executeQuery(qry);
            while (rs.next()) {
                Student p = new Student();
                p.setId(rs.getString("slno"));
                p.setName(rs.getString("name"));
                p.setemail(rs.getString("email"));
                p.setphone(rs.getString("phone"));
                //p.setdate(rs.getString("date"));
                al.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return al;
    }
	public ArrayList<Student> getUserDetails() {
		 Statement st=null;
		 ResultSet rs=null;
		 ArrayList<Student> al = new ArrayList<Student>();
		 try {
		 st = con.createStatement();
		 String qry = "select * from sookshma.sookshmas1 where slno not in(1);";
		 rs = st.executeQuery(qry);
		 while (rs.next()) {
		 Student p = new Student();
		 p.setId(rs.getString("slno"));
		 p.setName(rs.getString("name"));
		 p.setemail(rs.getString("email"));
		 p.setphone(rs.getString("phone"));
		 al.add(p);
		 }
		 } catch (Exception e) {
		 e.printStackTrace();
		 }
		 return al;
	}
	public String delete(int id) {
		Statement ps=null;
		int a;
		String status="";
		try {
			ps=con.createStatement();
			String sql="DELETE FROM sookshma.sookshmas1 WHERE slno='"+id+"';";
			a=ps.executeUpdate(sql);
			if(a>0) {
				status="sucess";
			}
			else {
				status="failure";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
// this is for forget password to check email
	public String forget(String email,String phone) {
		Statement ps=null;
		ResultSet rs;
		String status="";
		try {
			ps=con.createStatement();
			String sql="select * from sookshma.sookshmas1 where email='"+email+"' and phone='"+phone+"';";
			rs=ps.executeQuery(sql);
			if(rs.next()) {
				status="sucess";
				se.setAttribute("emails", email);
				se.setAttribute("phones", phone);
				se.setAttribute("ema", "found");
			}
			else {
				status="failure";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
// this is for updating the password
	public String updatePass(String pass) {
		String status = "";
        Statement st = null;
        int a;
        try {
            st = con.createStatement();
            String sql="update sookshma.sookshmas1 set pw='" + pass + "' where email='" + se.getAttribute("emails") + "';";            
            a=st.executeUpdate(sql);
            if(a > 0) {
            	status="sucess";
            	se.setAttribute("ema", null);
            }
            else{
            	status="failure";
            	se.setAttribute("ema", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
	}
	public String depositamt(int amt) {
		String status="";
		Statement st=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		try {
			String s=(String)se.getAttribute("email");
			st = con.createStatement();
            String sql="select balance from sookshma.sookshmas1 where email='"+s+"';";            
            rs=st.executeQuery(sql);
			boolean b = rs.next();
			if (b==true) {
                int bal = rs.getInt("balance");
                bal=bal+amt;
                String qry="update sookshma.sookshmas1 set balance='" + bal +"' where email='" + s + "';";
                st.executeUpdate(qry);
                String name=(String)se.getAttribute("uname");
                long id=(long) se.getAttribute("id");
               	ps=(PreparedStatement)con.prepareStatement("insert into sookshma.Banking(id,email,name,Deposit,Withdraw,Balance) values(?,?,?,?,?,?);");
               	ps.setLong(1, id);
                ps.setString(2, s);
                ps.setString(3, name);
                ps.setLong(4, amt);
                ps.setLong(5,0);
                ps.setLong(6, bal);
              	int c=ps.executeUpdate();
                if (c > 0) {	
                	se.setAttribute("balance", bal);
                   status = "success";
                }
            } 
			else {
                status = "failure";
            }
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	public String withdrawamt(int amt) {
		String status="";
		Statement st=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		try {
			String s=(String)se.getAttribute("email");
			st = con.createStatement();
            String sql="select balance from sookshma.sookshmas1 where email='"+s+"';";            
            rs=st.executeQuery(sql);
			boolean b = rs.next();
			if (b==true) {
                int bal = rs.getInt("balance");
                if(bal>=amt) {
                	bal=bal-amt;
                	String qry="update sookshma.sookshmas1 set balance='" + bal +"' where email='" + s + "';";
                	st.executeUpdate(qry);
                	String name=(String)se.getAttribute("uname");
                	long id=(long) se.getAttribute("id");
                	ps=(PreparedStatement)con.prepareStatement("insert into sookshma.Banking(id,email,name,Deposit,Withdraw,Balance) values(?,?,?,?,?,?);");
                	ps.setLong(1, id);
                	ps.setString(2, s);
                	ps.setString(3, name);
                	ps.setLong(4,0);
                	ps.setLong(5,amt);
                	ps.setLong(6, bal);
                	int c=ps.executeUpdate();
                	if (c > 0) {	
                		status = "success";
                		se.setAttribute("balance", bal);
                	}
                }else {
                	status="insufficent";
                }
             } 
             else {
                status = "failure";
            }
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	public String transfer(String phone,int amt) {
		String status="";
		Statement st=null,sts=null;
		ResultSet rs=null,rss=null;
		PreparedStatement ps=null,pss=null;
		try {
			String s=(String)se.getAttribute("email");
			st = con.createStatement();
            String sql="select balance from sookshma.sookshmas1 where email='"+s+"';";            
            rs=st.executeQuery(sql);
			boolean b = rs.next();
			if (b==true) {
                int bal = rs.getInt("balance");
                if(bal>=amt) {
                	bal=bal-amt;
                	String qry="update sookshma.sookshmas1 set balance='" + bal +"' where email='" + s + "';";
                	st.executeUpdate(qry);
                	String name=(String)se.getAttribute("uname");
                	long id=(long) se.getAttribute("id");
                	ps=(PreparedStatement)con.prepareStatement("insert into sookshma.Banking(id,email,name,Deposit,Withdraw,Balance) values(?,?,?,?,?,?);");
                	ps.setLong(1, id);
                	ps.setString(2, s);
                	ps.setString(3, name);
                	ps.setLong(4,0);
                	ps.setLong(5,amt);
                	ps.setLong(6, bal);
                	int c=ps.executeUpdate();
                	if(c>0) {
                		se.setAttribute("balance", bal);
            			sts = con.createStatement();
                        String sqls="select * from sookshma.sookshmas1 where phone ='"+phone+"';";            
                        rss=sts.executeQuery(sqls);
            			boolean bd = rss.next();
            			
            			if (bd==true) {
            				long uid=rss.getLong("slno");
            				String uname=rss.getString("name");
                            String uemail=rss.getString("email");
                            long balance = rss.getLong("balance");
                            balance=balance+amt;
                            status="debited";
                            String qary="update sookshma.sookshmas1 set balance='" + balance +"' where phone='" + phone + "';";
                            st.executeUpdate(qary);
                           	pss=(PreparedStatement)con.prepareStatement("insert into sookshma.Banking(id,email,name,Deposit,Withdraw,Balance) values(?,?,?,?,?,?);");
                           	pss.setLong(1, uid);
                            pss.setString(2, uemail);
                            pss.setString(3, uname);
                            pss.setLong(4, amt);
                            pss.setLong(5,0);
                            pss.setLong(6, balance);
                          	int cp=pss.executeUpdate();
                          	if(cp>0) {
                          		status="success";
                          	}
                          	else {
                          		status="failure";
                          	}
            			}else {
            				status="failure";
            			}
                	}else {
                		status="failure";
                	}
                }else {
                	status="insufficent";
                }
			}else {
				status="failure";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}

}


