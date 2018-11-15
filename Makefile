all:
	echo "Usage: make <startdb|setupfolders>"
setupfolders:
	./tools/setupfolders.sh
startdb: setupfolders
	./tools/startmongodb.sh