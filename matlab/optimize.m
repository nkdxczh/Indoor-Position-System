
temperature=500;      %初始温度
iter=20;               %内部蒙特卡洛循环迭代次数

r=5000;
x=rand()*r;
y=rand()*x/(3^0.5);
xita=rand()*2*pi;

l=1;                            %统计迭代次数
len(l)=stimulate(x,y,xita,map,r);   %每次迭代后的路线长度  

while temperature>0.01     %停止迭代温度
    
    for i=1:iter     %多次迭代扰动，一种蒙特卡洛方法，温度降低之前多次实验
        len1=stimulate(x,y,xita,map,r);        %计算原路线总距离
        [temx temy temxita]=netplot(x,y,xita,r);      %产生随机扰动
        len2=stimulate(temx,temy,temxita,map,r);     %计算新路线总距离
        
        delta_e=len2-len1;  %新老距离的差值，相当于能量
        if delta_e>0        %新路线好于旧路线，用新路线代替旧路线
            x=temx;
            y=temy;
            xita=temxita;
        else                        %温度越低，越不太可能接受新解；新老距离差值越大，越不太可能接受新解
            if exp(delta_e/temperature)>rand() %以概率选择是否接受新解
                x=temx;
                y=temy;
                xita=temxita;
            end
        end        
    end
    l=l+1;
    len(l)=stimulate(x,y,xita,map,r); 
    temperature=temperature*0.99;   %温度不断下降
  
end  

figure;
plot(len)  