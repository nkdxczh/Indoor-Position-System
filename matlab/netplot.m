function [ newx newy newxita ] = netplot( x,y,xita,r )
%UNTITLED4 Summary of this function goes here
%   Detailed explanation goes here


newx=normrnd(x,r/6);
while newx<0||newx>r
    newx=normrnd(x,r/6);
end

newy=normrnd((y/x)*newx,newx/(3^0.3*6));
while newy<0||newy>(newx/(3^0.3))
    newy=normrnd((y/x)*newx,newx/(3^0.3*6));
end

newxita=normrnd(xita,pi/3);
while newxita<0||newxita>2*pi
    if newxita<0
        newxita=newxita+2*pi;
    end
    if newxita>2*pi
        newxita=newxita-2*pi;
    end
end

end

