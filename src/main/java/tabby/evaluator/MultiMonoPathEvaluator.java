package tabby.evaluator;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.traversal.BranchState;
import org.neo4j.graphdb.traversal.Evaluation;
import org.neo4j.graphdb.traversal.PathEvaluator;
import tabby.data.State;

import java.util.List;


public class MultiMonoPathEvaluator extends PathEvaluator.Adapter<State> {

    private List<Node> endNodes;
    private int maxDepth;

    public MultiMonoPathEvaluator(List<Node> endNodes, int maxDepth) {
        this.endNodes = endNodes;
        this.maxDepth = maxDepth;
    }

    @Override
    public Evaluation evaluate(Path path, BranchState<State> state) {
        boolean includes = true;
        boolean continues = true;
        int length = path.length();
        if(length == 0) return Evaluation.of(false, true);

        Node node = path.endNode();

        if(length >= maxDepth){
            continues = false; 
            if(endNodes != null && !endNodes.contains(node)){
                includes = false; 
            }
        }else if(endNodes != null && endNodes.contains(node)){
            
            continues = false;
        } else {
            includes = false;
        }

        return Evaluation.of(includes, continues);
    }

    public static MultiMonoPathEvaluator of(List<Node> endNodes, int maxDepth){
        return new MultiMonoPathEvaluator(endNodes, maxDepth);
    }
}
