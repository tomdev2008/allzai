#/bin/bash 

sdk_call_game_api=$1
query=$2

curl -d '$query' --user-agent 'curl/UA-lalasdk' $sdk_call_game_api

exit
