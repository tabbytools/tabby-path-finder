package tabby.path;

import org.neo4j.graphalgo.EvaluationContext;
import org.neo4j.graphalgo.impl.path.TraversalPathFinder;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.PathExpander;
import org.neo4j.graphdb.traversal.Traverser;
import org.neo4j.graphdb.traversal.Uniqueness;
import org.neo4j.internal.helpers.collection.LimitingIterable;
import tabby.data.State;

import java.util.List;


public abstract class BasePathFinder extends TraversalPathFinder {

    public final PathExpander<State> expander;
    public final EvaluationContext context;
    public final int maxDepth;
    public final boolean depthFirst;
    private Traverser lastTraverser;

    public BasePathFinder(EvaluationContext context, PathExpander<State> expander, int maxDepth, boolean depthFirst) {
        this.expander = expander;
        this.context = context;
        this.maxDepth = maxDepth;
        this.depthFirst = depthFirst;
    }

    protected Uniqueness uniqueness()
    {

        return Uniqueness.RELATIONSHIP_PATH;
    }

    public Iterable<Path> findAllPaths(Node start, List<Node> ends){
        lastTraverser = instantiateTraverser( start, ends );
        Integer maxResultCount = maxResultCount();
        return maxResultCount != null ? new LimitingIterable<>( lastTraverser, maxResultCount ) : lastTraverser;
    }

    public Iterable<Path> findAllPaths(List<Node> starts, List<Node> ends){
        lastTraverser = instantiateTraverser( starts, ends );
        Integer maxResultCount = maxResultCount();
        return maxResultCount != null ? new LimitingIterable<>( lastTraverser, maxResultCount ) : lastTraverser;
    }

    protected abstract Traverser instantiateTraverser(Node start, List<Node> ends );

    protected abstract Traverser instantiateTraverser(List<Node> starts, List<Node> ends );
}
