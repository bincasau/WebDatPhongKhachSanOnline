package controll;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.KhachHang;

/**
 * Servlet implementation class DatPhongServlet
 */
@WebServlet("/DatPhongServlet")
public class DatPhongServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public DatPhongServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        KhachHang kh = (KhachHang) session.getAttribute("khachHang");

        String maPhong = request.getParameter("maPhong");
        String giaPhongStr = request.getParameter("giaPhong");
        double giaPhong = 0;

        if (giaPhongStr != null && !giaPhongStr.isEmpty()) {
            try {
                giaPhong = Double.parseDouble(giaPhongStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        if (kh == null) {
            response.sendRedirect("DangNhap.jsp");
        } else {
            request.setAttribute("maPhong", maPhong);
            request.setAttribute("giaPhong", giaPhong);
            request.getRequestDispatcher("FormDatPhong.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
