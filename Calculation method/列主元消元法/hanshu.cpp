#include <iostream>

#include <cmath>

#include<iomanip>

using namespace std;//这样命名空间内定义的所有标识符都有效，为了兼容旧版的代码和支持新的标准，命名空间封装的是标准程序库的名称，为了和之前的头文件区别，一般不加.h 

const int WEIDU=9;//我们要求解的是九阶的方阵 

int Solve(double Matrix[][WEIDU],double b[],int index)//用来求解的函数 

{
    
	int i,j,x,y;
	
    double d,t;
    
    for( x = 0 ;x < index - 1 ; x++ )//在下标零到7之间循环，最后一列不用打 
    
    {
    	
        d = 0.0;
        
        for (j = x ; j < index ; j++)//循环 
        
        {
        	
            t = fabs( Matrix[j][x] );//取出矩阵的元素 
            
            if( t > d ){
            	
			d = t;
			
			y = j;
			
			}//第一个如果矩阵的元素要大于0就更新d的值，后面是如果列要大于这个元素也更新d的值，并且用is来记录列下标 
            
        }//这个函数就是用来把行的元素移到最上面的 
        
        if (fabs(d) > 0)//交换完以后 
        
        {
        	
            if ( y != x)//如果第一个元素就是最大的 
            
            for (i=x;i< index;i++)//对后面的元素进行循环 
            
            {
            	
                t = Matrix[x][i];
                
                Matrix[x][i] = Matrix[y][i];
                
                Matrix[y][i] = t;//用t交换矩阵这一行的所有列 
                
            }

                t = b[x];
				
				b[x] = b[y];
				
				b[y] = t;//最后再将b中的值也给换了 
                
        }
        
        else//如果是一个零矩阵 
        
        {
        	
            cout<<"输入失败"<<endl;//输出求解失败 
            
            return (0);//直接返回 
            
        }
        
        d=Matrix[x][x];//然后d取对角元素的值 
        
        for (i=x+1;i<index;i++)// 
        
        {//这一行以后的行 
        	
            for(j=x+1;j<index;j++)//固定行以后所有的列 
            
                Matrix[i][j]=Matrix[i][j]-Matrix[i][x]*Matrix[x][j]/d;//下一行的打洞 
                
            b[i]=b[i]-b[x]*Matrix[i][x]/d;//并且将b也给换算了 
            
        }
        
    }

    d=Matrix[index-1][index-1]; //此时我们已经把原来的矩阵变成了上三角矩阵，接下来我们要把它变成对角矩阵，然后进行求解 
    
    if (fabs (d)>0)//此时如果最后一行非零，就可以继续求解，如果恰好最后一行是零，那么就输出输入矩阵失败 
    
    {
    	
        b[index-1]=b[index-1]/d;//从最后一行开始求解，这个式子就是最后一行的解 
        
        for (i=index-2;i>=0;i--)
        
        {
        	
            t=0.0;
            
            for (j=i+1;j<index;j++)//行固定以后，对每一列进行循环，然后不断往上打洞 
             
            t=t+Matrix[i][j]*b[j];
            
            b[i]=(b[i]-t)/Matrix[i][i];
            
        }
        
    }
    
     else
     
    {
    	
        cout<<"输入失败"<<endl;
        
        return (0);
        
    }
    
    return (1);
    
}

int main()//主函数部分 

{
	
    int i;
    
    double Matrix[WEIDU][WEIDU]=
    
    {
    	
       31,-13,0,0,0,-10,0,0,0,
       -13,35,-9,0,-11,0,0,0,0,
       0,-9,31,-10,0,0,0,0,0,
       0,0,-10,79,-30,0,0,0,-9,
       0,0,0,-30,57,-7,0,-5,0,
       0,0,0,0,-7,47,-30,0,0,
       0,0,0,0,0,-30,41,0,0,
       0,0,0,0,-5,0,0,27,-2,
       0,0,0,-9,0,0,0,-2,29

    };//这里定义了一个我们要求解的矩阵 
    
    double b[WEIDU]={-15,27,-23,0,-20,12,-7,7,10};//求解方程的右边的项 
    
   if( Solve(Matrix,b,WEIDU))//如果成功解出方程 
   
   {
        
        cout<<fixed; //就算最后一位是零也不往前缩进 
        
        for (i=0; i<WEIDU;i++)//循环维数 
        
        cout<<"X["<<i<<"]="<<setprecision(12)<<setw(15)<<b[i]<<endl;//输出得到的结果 
        
   }
   
}

