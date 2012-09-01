import org.neo4j.gis.spatial.osm.OSMImporter;
import org.neo4j.kernel.impl.batchinsert.BatchInserter;
import org.neo4j.kernel.impl.batchinsert.BatchInserterImpl;
import java.io.IOException;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.kernel.EmbeddedGraphDatabase;

class SpatialOsmImport {
  public static void main(String[] args)
  {
    OSMImporter importer = new OSMImporter("India");
    BatchInserter batchinserter = new BatchInserterImpl(args[1]);
    try{
      importer.importFile(batchinserter, args[0], false);
      GraphDatabaseService db = new EmbeddedGraphDatabase(args[1]);
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
