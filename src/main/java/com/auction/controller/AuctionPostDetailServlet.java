package com.auction.controller;

import com.auction.dao.AuctionDao;
import com.auction.model.Auction;
import com.auction.model.AuctionContainer;
import com.auction.model.Vehicle;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AuctionPostDetailServlet", value = "/auctionpostdetail")
public class AuctionPostDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int aucId = Integer.parseInt(request.getParameter("auctionid"));
        System.out.println(aucId);
        AuctionDao auctionDao;
        AuctionContainer ac;
        try {
            auctionDao = new AuctionDao();
            ac = auctionDao.getAuctionContainerByAuctionId(aucId);
            Vehicle v = ac.getVehicle();
            Auction a = ac.getAuction();
            request.setAttribute("auction", a);
            request.setAttribute("vehicle", v);
            getServletContext().getRequestDispatcher("/WEB-INF/views/auctionpostdetails.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
