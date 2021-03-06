package com.auction.controller;

import com.auction.dao.BidDao;
import com.auction.dao.MessageDao;
import com.auction.model.Bid;
import com.auction.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@WebServlet(name = "PlaceBidServlet", value = "/placebid")
public class PlaceBidServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BidDao bidDao;
        // Get bid info from browser
        int auctionId = Integer.parseInt(request.getParameter("auctionid"));
        int bidAmount = Integer.parseInt(request.getParameter("bid"));
        User user = (User) request.getSession().getAttribute("user");
        // Create bid object
        Bid b = new Bid();
        b.setAmount(bidAmount);
        b.setUsername(user.getUsername());
        b.setBid_datetime(LocalDateTime.now().toString());
        b.setAuction_id(auctionId);
        // Post bid to auction
        // TODO: Update auctions highest bidder if bid is new highest
        try {
            bidDao = new BidDao();
            bidDao.addBid(b);
            MessageDao msgDao = new MessageDao();

            // Send a message to everyone that a new highest bid has been posted

            ArrayList<String> usernames;
            usernames = bidDao.getBidders(auctionId);
            String msg = "A new highest bid of " + bidAmount + " has been posted on auction " + auctionId;
            for(String username : usernames) {
                msgDao.addMessage(msg, username);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }


        response.sendRedirect("/userhome");
    }
}
