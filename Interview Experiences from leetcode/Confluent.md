Questions are gathered from Leetcode and other sources 


================================
Telephonic.
1.) Do basic string match. Assume only one * in the pattern.
Follow up: Assume any number of *s

-> solve using dp or back trackinghttps://leetcode.com/problems/wildcard-matching/solutions/1001130/c-clean-and-concise-bottom-up-dp-code-with-detailed-explanation-easy-to-understand/

Onsite
1.) Windowed Average. Your are given a set of key, value pair. Each key, value expires after k millisec. I can ask you to get me a specific key. Also, I can ask you to return me the average. The catch was to make sure to pruce the DLL before each call. For ex: if <"foo", 100> is saved at t = 1 and time expiry is 3ms then after 3 ms get("foo") should return key not found.
2.) Task scheduler. You are given a list of runnables + delay that after t ms run this runnable. How do you store it? The idea was not to design the PQ. but the idea was to handle multithreading part. Cz you need a producer to put jobs on PQ as well as a consumer which will actually schedule the jobs
3.) Design bit.ly system.
4.) Linux deep dive
Very basic CS questions.

DNS. Why hierarchy
How does routing table get populated.
Ping vs tracerout vs telnet.
File system
Process table/context switch
What is /bin /usr
What is NAT
VPN and VPC
TCP vs UDP
HTTP + methods + cookie
5.) Googlyness kind of round. The only question that I found interesting was that you can only ask one question to the candidate and you want to decide the cultural fit accordingly, what will be that question? For me, it would be what have you learnt in the past 30 days technically + non-technically (behavioral) level about yourself.

Pending result. Not too optimistic because I am not great at the "talking" part - tell me about a time when XYZ happened blah blah

Comments: 16


====================================



Suppose you are building a library and have following definition of a function and two methods register and findMatches. Register method registers a function and its argumentTypes in the library and findMatches accepts an input argument list and tries to find all the functions that match this input argument list.

class Function {
	String name;
	List<String> argumentTypes;
	boolean isVariadic;
	
	Function(String name, List<String> argumentTypes, boolean isVariadic) {
		this.name = name;
		this.argumentTypes = argumentTypes;
		this.isVariadic = isVariadic;
	}
}

class FunctionLibrary {
	public void register(Set<Function> functionSet)  {
		// implement this method.
	}
	
	public List<Function> findMatches(List<String> argumentTypes) {
		// implement this method.
		return null;
	}
}
Note: If a function is marked as isVariadic=true, then the last argument can occur one or more number of times.

Example: FuncA: [String, Integer, Integer]; isVariadic = false FuncB: [String, Integer]; isVariadic = true FuncC: [Integer]; isVariadic = true FuncD: [Integer, Integer]; isVariadic = true FuncE: [Integer, Integer, Integer]; isVariadic = false FuncF: [String]; isVariadic = false FuncG: [Integer]; isVariadic = false

findMatches({String}) -> [FuncF] findMatches({Integer}) -> [FuncC, FuncG] findMatches({Integer, Integer, Integer, Integer}) -> [FuncC, FuncD] findMatches({Integer, Integer, Integer}) -> [FuncC, FuncD, FuncE] findMatches({String, Integer, Integer, Integer}) -> [FuncB] findMatches({String, Integer, Integer}) -> [FuncA, FuncB]

What is an optimal solution for this?



-----> ans by claude 

import java.util.*;

class FunctionLibrary {
    private TrieNode root;

    public FunctionLibrary() {
        root = new TrieNode();
    }

    public void register(Set<Function> functionSet) {
        for (Function function : functionSet) {
            TrieNode node = root;
            for (int i = 0; i < function.argumentTypes.size(); i++) {
                String argType = function.argumentTypes.get(i);
                node = node.getOrCreateChild(argType);
            }
            if (function.isVariadic) {
                node.isVariadic = true;
            }
            node.functions.add(function);
        }
    }

    public List<Function> findMatches(List<String> argumentTypes) {
        List<Function> matches = new ArrayList<>();
        TrieNode node = root;
        for (int i = 0; i < argumentTypes.size(); i++) {
            String argType = argumentTypes.get(i);
            if (node == null) {
                break;
            }
            node = node.getChild(argType);
            if (node == null && i == argumentTypes.size() - 1) {
                matches.addAll(collectVariadicMatches(root, argumentTypes));
                break;
            }
        }
        if (node != null) {
            matches.addAll(node.functions);
            matches.addAll(collectVariadicMatches(node, argumentTypes.subList(node.depth, argumentTypes.size())));
        }
        return matches;
    }

    private List<Function> collectVariadicMatches(TrieNode node, List<String> remainingArgs) {
        List<Function> matches = new ArrayList<>();
        if (node.isVariadic) {
            matches.addAll(node.functions);
        }
        for (TrieNode child : node.children.values()) {
            matches.addAll(collectVariadicMatches(child, remainingArgs));
        }
        return matches;
    }

    private static class TrieNode {
        Map<String, TrieNode> children;
        List<Function> functions;
        boolean isVariadic;
        int depth;

        TrieNode() {
            children = new HashMap<>();
            functions = new ArrayList<>();
            isVariadic = false;
            depth = 0;
        }

        TrieNode getOrCreateChild(String argType) {
            TrieNode child = children.get(argType);
            if (child == null) {
                child = new TrieNode();
                child.depth = depth + 1;
                children.put(argType, child);
            }
            return child;
        }

        TrieNode getChild(String argType) {
            return children.get(argType);
        }
    }
}