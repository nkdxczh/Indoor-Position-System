
temperature=500;      %��ʼ�¶�
iter=20;               %�ڲ����ؿ���ѭ����������

r=5000;
x=rand()*r;
y=rand()*x/(3^0.5);
xita=rand()*2*pi;

l=1;                            %ͳ�Ƶ�������
len(l)=stimulate(x,y,xita,map,r);   %ÿ�ε������·�߳���  

while temperature>0.01     %ֹͣ�����¶�
    
    for i=1:iter     %��ε����Ŷ���һ�����ؿ��巽�����¶Ƚ���֮ǰ���ʵ��
        len1=stimulate(x,y,xita,map,r);        %����ԭ·���ܾ���
        [temx temy temxita]=netplot(x,y,xita,r);      %��������Ŷ�
        len2=stimulate(temx,temy,temxita,map,r);     %������·���ܾ���
        
        delta_e=len2-len1;  %���Ͼ���Ĳ�ֵ���൱������
        if delta_e>0        %��·�ߺ��ھ�·�ߣ�����·�ߴ����·��
            x=temx;
            y=temy;
            xita=temxita;
        else                        %�¶�Խ�ͣ�Խ��̫���ܽ����½⣻���Ͼ����ֵԽ��Խ��̫���ܽ����½�
            if exp(delta_e/temperature)>rand() %�Ը���ѡ���Ƿ�����½�
                x=temx;
                y=temy;
                xita=temxita;
            end
        end        
    end
    l=l+1;
    len(l)=stimulate(x,y,xita,map,r); 
    temperature=temperature*0.99;   %�¶Ȳ����½�
  
end  

figure;
plot(len)  