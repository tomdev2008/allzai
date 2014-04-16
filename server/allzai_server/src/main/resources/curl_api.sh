#/bin/bash 

curl_call_api=$1
query=$2

curl -d '$query' --user-agent 'curl/UA-allzai' $curl_call_api

exit
