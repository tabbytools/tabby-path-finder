package tabby.expander.processor;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import tabby.calculator.Calculator;
import tabby.data.State;


public interface Processor {

    void init(Node node, State preState, Relationship lastRelationship);

    Relationship process(Relationship next);


    boolean isNeedProcess();

    State getNextState();

    void setCalculator(Calculator calculator);

    boolean isLastRelationshipTypeAlias();

    Processor copy();
}
