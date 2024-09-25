package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import dao.PersonDAO;

import entities.Person;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/PersonServlet")
public class PersonServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PersonDAO personDAO=new PersonDAO();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {		
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html lang=\"en\">\r\n"
            		+ "<head>\r\n"
            		+ "    <meta charset=\"UTF-8\">\r\n"
            		+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
            		+ "    <style>\r\n"
            		+ "        /* CSS cho bảng */\r\n"
            		+ "        table {\r\n"
            		+ "            width: 100%;\r\n"
            		+ "            border-collapse: collapse;\r\n"
            		+ "            border: 1px solid #ddd;\r\n"
            		+ "        }\r\n"
            		+ "        th, td {\r\n"
            		+ "            padding: 8px;\r\n"
            		+ "            text-align: left;\r\n"
            		+ "            border-bottom: 1px solid #ddd;\r\n"
            		+ "        }\r\n"
            		+ "        th {\r\n"
            		+ "            background-color: #f2f2f2;\r\n"
            		+ "        }\r\n"
            		+ "        /* CSS cho trường nhập văn bản */\r\n"
            		+ "        input[type=\"text\"] {\r\n"
            		+ "            width: 100%;\r\n"
            		+ "            padding: 8px;\r\n"
            		+ "            margin-bottom: 10px;\r\n"
            		+ "            border: 1px solid #ccc;\r\n"
            		+ "            border-radius: 4px;\r\n"
            		+ "        }\r\n"
            		+ "    </style>\r\n"
            		+ "    <title>Thong tin ca nhan</title>\r\n"
            		+ "</head>");
            out.println("<body>");
            out.println("<h4>Thông tin nguoi</h4>");
            out.println("<form method=\"post\">\r\n"
            		+ "        <div style=\"width: 50%; margin-left: 200px;\">\r\n"
            		+ "            <input type=\"text\" name=\"id\" placeholder=\"Id\">\r\n"
            		+ "            <input type=\"text\" name=\"firstname\" placeholder=\"First Name\">\r\n"
            		+ "            <input type=\"text\" name=\"lastname\" placeholder=\"Last Name\">\r\n"
            		+ "            <input type=\"text\" name=\"address\" placeholder=\"Address\">\r\n"
            		+ "            <input type=\"text\" name=\"bio\" placeholder=\"Bio\">\r\n"
            		+ "        </div>    \r\n"
            		+ "        <input type=\"submit\" name=\"add\" value=\"Add\">\r\n"
            		+ "        <input type=\"submit\" name=\"delete\" value=\"Delete\">\r\n"
            		+ "        <input type=\"submit\" name=\"update\" value=\"Update\">   \r\n"
            		+ "    </form>");
            out.println("<table>");
            out.println("<tr>\r\n"
            		+ "            <th>Id</th>\r\n"
            		+ "            <th>First Name</th>\r\n"
            		+ "            <th>Last Name</th>\r\n"
            		+ "            <th>Address</th>\r\n"
            		+ "            <th>Bio</th>\r\n"
            		+ "        </tr>");
            for (Person person: personDAO.getPersons()) {
                out.println("<tr>");
                out.println("<td>" + person.getId() + "</td>");
                out.println("<td>" + person.getFirstname() + "</td>");
                out.println("<td>" + person.getLastname() + "</td>");
                out.println("<td>" + person.getAddress() + "</td>");
                out.println("<td>" + person.getBio() + "</td>");
                out.println("</tr>");
            }
            //out.println("<p>"+car_DAO.getListCarsString()+"</p>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } 
        
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	//Kiem tra button
    	String buttonNameAdd=request.getParameter("add");
    	String buttonNameDelete=request.getParameter("delete");
    	String buttonNameUpdate=request.getParameter("update");
    	if(buttonNameAdd!=null) {
    		String id = request.getParameter("id");
            String firstname=request.getParameter("firstname");
            String lastname=request.getParameter("lastname");
            String address=request.getParameter("address");
            String bio = request.getParameter("bio");
            //cars.clear();
            // Validate input (e.g., check if newId is numeric)

            // Add the new car to your List<Car>
            if (id != null && firstname != null && lastname != null && address!=null && bio!=null) {
                Person person=new Person(id, firstname, lastname, address, bio);
                if(personDAO.addPerson(person))
                	response.sendRedirect(request.getContextPath() + "/PersonServlet");
            }
            
    	}
    	if(buttonNameDelete!=null) {
    		String id =request.getParameter("id");
        	if(personDAO.deletePerson(id)) {
        		//errorNotify="";
        		response.sendRedirect(request.getContextPath() + "/PersonServlet");
        	}
    	}
    	if(buttonNameUpdate!=null) {
    		String id = request.getParameter("id");
            String firstname=request.getParameter("firstname");
            String lastname=request.getParameter("lastname");
            String address=request.getParameter("address");
            String bio = request.getParameter("bio");
            if (id != null && firstname != null && lastname != null && address!=null && bio!=null) {
                Person person=new Person(id, firstname, lastname, address, bio);
                if(personDAO.updatePerson(person))
                	response.sendRedirect(request.getContextPath() + "/PersonServlet");
            }
            
    	}  
    } 

}
