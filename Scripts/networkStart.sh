#1/bin/bash
 
echo "Network Start Script is now running "
echo "starting in 10 seconds"
sleep 1
echo "Press Ctrl +c to cancel The Script......."
sleep 10
echo "starting Waterfall Proxy"  
bash -c 'cd /home/network/minecraft/waterfall && ./start.sh'
./start.sh
sleep 30
echo "Starting Server Hub "
bash -c 'cd /home/network/minecraft/hub && ./start.sh'
sleep 30
echo "Network backbone is now active."




# screen -d -m -S hub bash -c 'cd /home/network/hub && ./start.sh'