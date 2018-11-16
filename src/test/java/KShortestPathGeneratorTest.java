import org.graphstream.graph.Graph;
import org.junit.Test;
import utils.ConfigFiles;
import utils.GraphManager;
import utils.KShortestPathGenerator;

import java.io.File;
import java.net.URISyntaxException;

public class KShortestPathGeneratorTest {

   @Test
   public void inputParameters() throws URISyntaxException {
      final String TOPOLOGY = "example";
      String path = new File(ConfigFiles.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParent();
      Graph graph = GraphManager.importTopology(path, TOPOLOGY);
      KShortestPathGenerator kShortestPathGenerator = new KShortestPathGenerator(graph, 10, 5, path, TOPOLOGY);
      kShortestPathGenerator.run();
   }
}
