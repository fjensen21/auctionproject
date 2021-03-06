package com.auction.controller;

import com.auction.dao.AuctionDao;
import com.auction.dao.BidDao;
import com.auction.dao.CustomerServiceDao;
import com.auction.dao.ReportsDao;
import com.auction.model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*
        ;
import java.io.Console;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "ReportsServlet", value = "/reports")
public class ReportsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        try {
            ReportsDao reportsDao = new ReportsDao();
            String bestBuyer = reportsDao.getBestBuyer();
            request.setAttribute("bestBuyer",bestBuyer);

        } catch(SQLException e) {
            e.printStackTrace();
        }

        try {
            ReportsDao reportsDao = new ReportsDao();
            Double totalEarnings = reportsDao.getTotalEarnings();
            request.setAttribute("totalEarnings",totalEarnings);

        } catch(SQLException e) {
            e.printStackTrace();
        }

        try {
            ReportsDao reportsDao = new ReportsDao();
            HashMap<String, Double> earningsPerUser = reportsDao.getEarningsPerUser();
            request.setAttribute("earningsPerUser",earningsPerUser);

        } catch(SQLException e) {
            e.printStackTrace();
        }

        try {
            ReportsDao reportsDao = new ReportsDao();
            HashMap<Integer, Double> earningsPerItem = reportsDao.getEarningsPerItem();
            request.setAttribute("earningsPerItem",earningsPerItem);

        } catch(SQLException e) {
            e.printStackTrace();
        }

        try {
            ReportsDao reportsDao = new ReportsDao();
            ArrayList<Double> earningsPerItemType = reportsDao.getEarningsPerItemType();
            request.setAttribute("earningsPerItemType",earningsPerItemType);

        } catch(SQLException e) {
            e.printStackTrace();
        }

        try {
            ReportsDao reportsDao = new ReportsDao();
            String bestItem = reportsDao.getBestSellingItems();
            request.setAttribute("bestItem",bestItem);

        } catch(SQLException e) {
            e.printStackTrace();
        }


        getServletContext().getRequestDispatcher("/WEB-INF/views/reports.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/reports.jsp").forward(request, response);
    }
}
