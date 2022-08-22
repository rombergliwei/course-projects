#include <iostream>
#include <random>
#include <ctime>
#include <cmath>
#include <cstdio>
#include <stdlib.h>

#define NUM_PULSE 100000

double H2(double p);//熵函数
double try_edet(double rate);//用蒙特卡洛法仿真出实验中的实际错误探测率，传入参数为理论错误探测率
double try_R(double distance);//仿真每个距离下的安全密钥生成率
using namespace std;

int main()
{
    FILE *fp = fopen("F:/ans.txt", "w");
    cout<<"L\tR\t\tlog R\n";
    for(double i = 0; i < 200; i += 1)
    {
        double temp = try_R(i);
        cout<<i<<'\t'<<temp<<'\t'<<log10(temp)<<endl;
        fprintf(fp, "%d\t%.10lf\n", int(i), temp);
    }
    return 0;
}

double try_R(double distance)
{
    static const double alpha = 0.21;
    static const double Y0 = 8.5e-7;
    static const double etaBob = 0.045;
    static const double eDet = 0.033;
    static const double mu = 0.6;
    static const double nu = 0.2;
    static const double correct = 1.22;

    double eta = etaBob * pow(10, -alpha * distance / 10);
    double Qmu = Y0 + 1 - exp(-eta * mu);
    double Qnu = Y0 + 1 - exp(-eta * nu);
    double EQmu = Y0/2 + (1 - exp(-eta * mu))*try_edet(eDet);
    double EQnu = Y0/2 + (1 - exp(-eta * nu))*try_edet(eDet);

    double Pmu1 = exp(-mu) * mu;
    double YL1 = mu / (mu * nu - nu * nu) * (
            exp(nu) * Qnu
                - nu*nu / (mu*mu) * exp(mu) * Qmu
                - (mu*mu - nu*nu)/(mu*mu) * Y0);
    double eU1 = (EQnu * exp(nu) - Y0 /2)/(nu * YL1);
    double R = 0.5*(-Qmu*correct*H2(EQmu/Qmu)
                + Pmu1*YL1 * (1 - H2(eU1))
                );
    return (R<0?0:R);
}

double try_edet(double rate)
{

    double sum = 0;
    for(int i = 0; i < NUM_PULSE; ++i)
    {
        sum += (rand() < rate ? 1 : 0);
    }
    return sum/NUM_PULSE;
}

double H2(double p)
{
    return -p * log2(p) - (1-p) * log2(1-p);
}
