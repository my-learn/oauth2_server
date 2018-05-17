JAVA_OPTS="-Xms128m -Xmx256m"

echo "oauth2_server on...."

if find -name oauth2_server.pid | grep "oauth2_server.pid";
then
  echo "oauth2_server is running..."
  exit
fi

nohup java $JAVA_OPTS -jar oauth2_server-1.0-SNAPSHOT.jar >  output 2>&1 &

if [ ! -z "oauth2_server.pid" ]; then
  echo $!> oauth2_server.pid
fi