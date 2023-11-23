#include <iostream>
#include<cmath>
#include<algorithm>
#include<string>

using namespace std;


int  find_redundant(int k,string str){
        string ans="";
        int l=0;
        for(int i=k;i<str.size();i+=(2*(k+1))){
             ans+=str.substr(i,k+1);
        }
        for(int i=0;i<ans.length();i++){
            
            l^=int(ans[i]-'0');
        }
       
        return l;
}


int main() {
    cout<<"Enter the data recieved"<<endl;

    string s;
    cin >> s;
    reverse(s.begin(), s.end());
   
    string cod="";
    

    for (int i = 0; i < s.size(); i++) {
        int k = (1 << i) - 1;
        if (k < s.size()) {
            cod+=char('0'+find_redundant(k,s));
        }
    }
    reverse(cod.begin(),cod.end());
    cout<<"ERROR DETECTED AT POSITION:- ";
     cout<<stoi(cod, 0, 2);
    

    return 0;
}
