package tabby.expander.processor;


public class ProcessorFactory {

    public static Processor newInstance(String name){
        if ("JavaGadget".equals(name)) {
            return new JavaGadgetProcessor();
        }else if("JavaGadgetBackward".endsWith(name)){
            return new JavaGadgetBackwardProcessor();
        }
        return new CommonProcessor();
    }
}
