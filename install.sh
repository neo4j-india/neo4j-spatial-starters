#!/bin/bash

wget http://dist.neo4j.org/neo4j-community-1.7.2-unix.tar.gz

git://github.com/neo4j/spatial.git
cd spatial
mvn -Dmaven.test.skip=true install
cd ..
git clone git://github.com/neo4j/graph-collections.git
cd graph-collections
mvn -Dmaven.test.skip=true install
cd ..

wget -O geotools.zip http://sourceforge.net/projects/geotools/files/GeoTools%208.0%20Releases/8.0-RC2/geotools-8.0-RC2-bin.zip/download
unzip geotools.zip

CLASSPATH=`pwd`/spatial/neo4j-spatial-0.9-SNAPSHOT.jar
for f in geotools*/*.jar
do
    CLASSPATH=$CLASSPATH:`pwd`/"$f"
done

for file in neo4j-community-1.7*/lib/*.jar
do
    CLASSPATH=$CLASSPATH:`pwd`/"$file"
done
export CLASSPATH

wget http://download.geofabrik.de/osm/europe/monaco.osm.bz2
bunzip2 monaco.osm.bz2

javac SpatialOsmImport.java
