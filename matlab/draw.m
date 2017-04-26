function [ point ] = draw( movex,movey,xita,map )

minx=min(min(map(:,1)),min(map(:,3)));
miny=min(min(map(:,2)),min(map(:,4)));
map(:,1)=map(:,1)-minx;
map(:,2)=map(:,2)-miny;
map(:,3)=map(:,3)-minx;
map(:,4)=map(:,4)-miny;
maxy=max(max(map(:,2)),max(map(:,4)));
maxx=max(max(map(:,1)),max(map(:,3)));
R=(maxx^2+maxy^2)^0.5;
area=0;
get=0;

r=5000;
rota=[[cos(xita),-sin(xita)];[sin(xita),cos(xita)]];

point=[];
i=-r*floor(R/r);
ii=1;
jj=1;


while i<r*floor(R/r)+r
    j=-r/(3^0.5)*floor(R/(r/(3^0.5)));
    while j<r/(3^0.5)*floor(R/(r/(3^0.5)))+r/(3^0.5)
        if mod(ii+jj,2)==1
            jj=jj+1;
            j=j+r/(3^0.5);
            continue;
        end
        x=i+movex;
        y=j++movey;
        temp=[x,y];
        temp=temp*rota;
        if (temp(1,1)>0&&temp(1,1)<maxx&&temp(1,2)>0&&temp(1,2)<maxy)
            if(judge(temp(1,1),temp(1,2),map)==1)
                point=[point;temp];
            end
        end
%         point=[point;temp];
        jj=jj+1;
        j=j+r/(3^0.5);
    end
    ii=ii+1;
    i=i+r;
end


[sumd,p]=edge(point, map,r);

for i=1:size(point)
    plot(point(i,1),point(i,2),'r.');
    hold on;
end

for i=1:size(p)
    plot(p(i,1),p(i,2),'rO');
    hold on;
end

for i=1:size(map)
    plot([map(i,1),map(i,3)],[map(i,2),map(i,4)]);
    plot(map(i,1),map(i,2));
    hold on;
end

end
