#include <iostream>
#include<cmath>
#include<algorithm>
#include<string>
using namespace std;
int  find_redundant_(int k,string str){
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
void error_detection(){
  cout<<"Enter the data recieved"<<endl;
    string s;
    cin >> s;
    reverse(s.begin(), s.end());
    string cod="";
    for (int i = 0; i < s.size(); i++) {
        int k = (1 << i) - 1;
        if (k < s.size()) {
            cod+=char('0'+find_redundant_(k,s));
        }
    }
    reverse(cod.begin(),cod.end());

    if(stoi(cod, 0, 2)==0){
        cout<<"NO ERROR"<<endl;
    }
    else{
        cout<<"ERROR"<<endl;
        cout<<"The error is at position "<<stoi(cod, 0, 2)<<endl;
        
    }
     
}
int  find_redundant(int k,string str){
        string ans="";
        int l=0;
        for(int i=k;i<str.size();i+=(2*(k+1))){
             ans+=str.substr(i,k+1);
        }
        for(int i=0;i<ans.length();i++){
            if(ans[i]!='r'){
            l^=int(ans[i]-'0');}
        }
        return l;
}


int main() {
    cout<<"Enter the data to encode"<<endl;
    string s;
    cin >> s;
    reverse(s.begin(), s.end());
    int n = s.size();
    int r=0;
    while ((1 << r) < n + r + 1) {
        r++;
    }
    string cod(n + r, '0');  
     for (int i = 0; i < n + r; i++) {
        int k = (1 << i) - 1;
        if (k < n + r) {
            cod[k] = 'r';
        }
    }
    int i = 0;
    for (auto it = cod.begin(); it != cod.end(); ++it) {
        if (*it != 'r' && i < s.length()) {
            *it = s[i];
            i++;
        }
    }
    for (int i = 0; i < n + r; i++) {
        int k = (1 << i) - 1;
        if (k < n + r) {
            cod[k] = char('0'+find_redundant(k,cod));
        }
    }
    reverse(cod.begin(), cod.end());
    cout<<"This is the hamming code for the given data: ";
    for (char x : cod) {
        cout << x;
    }
    cout<<endl;
    error_detection();
    return 0;
}
