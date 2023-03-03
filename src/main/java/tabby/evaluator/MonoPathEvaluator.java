package tabby.evaluator;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.traversal.BranchState;
import org.neo4j.graphdb.traversal.Evaluation;
import org.neo4j.graphdb.traversal.PathEvaluator;
import tabby.data.State;


public class MonoPathEvaluator extends PathEvaluator.Adapter<State> {

    private Node endNode;
    private int maxDepth;

    public MonoPathEvaluator(Node endNode, int maxDepth) {
        this.endNode = endNode;
        this.maxDepth = maxDepth;
    }

    @Override
    public Evaluation evaluate(Path path, BranchState<State> state) {
        boolean includes = true;
        boolean continues = true;
        int length = path.length();
        Node node = path.endNode();

        if(length >= maxDepth){
            continues = false; 
            if(endNode != null && !endNode.equals(node)){
                includes = false; 
            }
        }else if(length == 0){ 
            includes = false;
        } else if(endNode != null && endNode.equals(node)){
            
            continues = false;
        } else {
            includes = false;
        }

        return Evaluation.of(includes, continues);
    }

    public static MonoPathEvaluator of(Node endNode, int maxDepth){
        return new MonoPathEvaluator(endNode, maxDepth);
    }
}
