function [ dis, p ] = edge( point, map,r )
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here\
dis=[];
p=[];
r=r;
for j=1:size(map)
    count=0;
    for i=1:size(point)
        x=point(i,1);
        y=point(i,2);
        x1=map(j,1);
        y1=map(j,2);
        x2=map(j,3);
        y2=map(j,4);
        if x1==x2
            if y<min(y1,y2)||y>max(y1,y2)
                continue;
            end
            temd=abs(x-x1);
            if temd<=r
                dis=[dis,temd];
                p=[p;point(i,:)];
                count=count+1;
            end
            continue;
        end
        if y1==y2
            if x<min(x1,x2)||x>max(x1,x2)
                continue;
            end
            temd=abs(y-y1);
            if temd<=r
                p=[p;point(i,:)];
                dis=[dis,temd];
                count=count+1;
            end
            continue;
        end
        d=[distance(x1,y1,x2,y2),distance(x1,y1,x,y),distance(x,y,x2,y2)];
        if d(3)^2-d(1)^2-d(2)^2>0
            continue;
        end
        if d(2)^2-d(1)^2-d(3)^2>0
            continue;
        end
        k=(x1-x2)/(y1-y2);
        temd=abs((k*y-k*y2-x+x2)/((1+k^2)^0.5));
        if temd<=r
            p=[p;point(i,:)];
            dis=[dis,temd];
            count=count+1;
        end
    end
end

end

