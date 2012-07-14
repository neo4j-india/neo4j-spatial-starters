import org.neo4j.gis.spatial.osm.OSMImporter;
import org.neo4j.kernel.impl.batchinsert.BatchInserter;
import org.neo4j.kernel.impl.batchinsert.BatchInserterImpl;
import java.io.IOException;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.kernel.EmbeddedGraphDatabase;

class SpatialOsmImport {
  public static void main(String[] args)
  {
    System.out.println("Looks like the OSMImporter was imported! :P");
    OSMImporter importer = new OSMImporter("India");
    BatchInserter batchinserter = new BatchInserterImpl("/tmp/neo");
    try{
      importer.importFile(batchinserter, "/Volumes/Software/neo4j/spatial_poc/india.osm", false);
      GraphDatabaseService db = new EmbeddedGraphDatabase("/tmp/neo");
      importer.reIndex(db, 10000);
      db.shutdown();
    }
    catch(Exception e)
    {
      System.out.println(e.getMessage());
    }
    batchinserter.shutdown();
  }
}
