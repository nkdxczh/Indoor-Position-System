function output = judge( x,y,map )
%UNTITLED2 Summary of this function goes here
%   Detailed explanation goes here
output=0;
up=0;
down=0;
left=0;
right=0;
for i=1:size(map)
    if (x-map(i,1))*(x-map(i,3))<0
        if(map(i,1)~=map(i,3))
            temy=(x-map(i,3))*(map(i,2)-map(i,4))/(map(i,1)-map(i,3))+map(i,4);
            if (y>temy)
                down=down+1;
            end
            if (y<temy)
                up=up+1;
            end
            if y==temy
                output=1;
                break;
            end
        end
    end
end
if output==0
    if(mod(down,2)==1||mod(up,2)==1)
        output=1;
    end
    if(down==0||up==0)
        output=0;
    end
end
end

