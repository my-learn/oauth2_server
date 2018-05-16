echo "Killing: `cat oauth2_server.pid`"
kill -9 `cat oauth2_server.pid`
rm -rf oauth2_server.pid