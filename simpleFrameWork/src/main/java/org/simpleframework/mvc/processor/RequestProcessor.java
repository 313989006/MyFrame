package org.simpleframework.mvc.processor;

import org.simpleframework.mvc.RequestProcessorChain;

/**
 * @InterfaceName RequestProcessor
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/25 15:20
 **/
public interface RequestProcessor {

    boolean process(RequestProcessorChain requestProcessorChain) throws Exception;
}
