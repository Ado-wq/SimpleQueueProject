import java.util.Scanner;
import java.util.Arrays;
public class Queue {
		
		// declare important state variables as static up here!
		static String [] names; 
		static final String EMPTY_SLOT = "-";
	
		
		// decide beforehand which commands exist in this program
	    enum Command {
	        ADD, // should add to the queue
	        PROCESS, // should process the queue
	        RESET, // should reset the queue
	        QUIT, // should quit the program
	        HELP, // should post commands
	        UNKNOWN // should send message that code is unknown or wrong
	    }

	   
	    public static Command parseCommand(String userInput) {
	    	
	    	
	    	String commandString = userInput.split(" ")[0]; // "<kommando>"
	    	
	    	if (commandString.equals("add")) {
	    		return Command.ADD;
	    	} else if (commandString.equals("process")) {
	    		return Command.PROCESS;
	    	}else if (commandString.equals("reset")) {
	    			return Command.RESET;
	    	} 
	    	else if (commandString.equals ("help")) {
	    		return Command.HELP;
	    	}else if (commandString.equals("quit")) {
	    		return Command.QUIT;
	    	} else {
	    		return Command.UNKNOWN;
	    	}

	    }
	    
	    public static String[] parseArguments(String userInput) {
	    	
	    	 
	        String[] commandAndArguments = userInput.split(" "); 
	        
	        String[] arguments = new String[commandAndArguments.length - 1]; 
	        for (int i=1; i<commandAndArguments.length; i++) {  
	       	 arguments[i-1] = commandAndArguments[i];
	        }
	        return arguments; 
	    }
	    
	    	public static void handleCommandAdd(String[] arguments) {
	    		String[] nameToAdd = new String [arguments.length];
	    		for (int i=0; i<arguments.length; i++) {
	    			nameToAdd[i] = arguments[i];
	    		}
	    		
	    		for(int p = 0; p<nameToAdd.length; p++) {
	                boolean isThereAFreeSlot = false;

	            for (int i=0; i<names.length; i++) {
	                if (names[i] == EMPTY_SLOT && names[7] == EMPTY_SLOT) {

	                		if ( nameToAdd[p].length() < 10 && nameToAdd[p].equals(nameToAdd[p].toLowerCase())) {

	                        isThereAFreeSlot = true;
	                            names[i] = nameToAdd[p];
	                            break;

	                        }else if (nameToAdd[p].length() > 10 && nameToAdd[p].equals(nameToAdd[p].toLowerCase())){
	                            isThereAFreeSlot = true;
	                            System.out.println("\n" +  nameToAdd[p] + " is to long, skipping this name");
	                            break;
	                        }else if (nameToAdd[p].length() > 10 && !nameToAdd[p].equals(nameToAdd[p].toLowerCase())) {
	                            isThereAFreeSlot = true;
	                            System.out.println("\n" +  nameToAdd[p] + " is invalid, skipping this name, typ help for more info");
	                            break;
	                        }else if (!nameToAdd[p].equals(nameToAdd[p].toLowerCase())) {
	                            isThereAFreeSlot = true;
	                            System.out.println("\n" +  nameToAdd[p] + " is invalid ,skipping this name, type Help for more info");
	                            break;
	                        }
	                            if (p == nameToAdd.length-1) {
	                                break;
	                            }

	                    }

	                }
	                        if (!isThereAFreeSlot) {
	                            System.out.println("\n" +  "Queue is full. " + nameToAdd[p] + " skipped this name");

	                            }
	            }
	        }
        
	    
	    public static void handleCommandProcess(String[] args) {
	 	
	    	for (int i=0; i<names.length-1; i++) {
	    		names[i] = names[i+1];
	    	} 
	    	names[7] = EMPTY_SLOT;
	    	System.out.println("queue has been processed");
	      
	    }
	    
	    public static void handleCommandReset(String[] args) {
	    	for(int i=0;i<names.length;i++)
	    		names[i] =EMPTY_SLOT;
	    	System.out.println("queue has been rested");
		}
	    public static void handleCommandHelp(String[] args) {
	    	String add = "add - adds name to queue, max 10 chars and must be lowercases";
	    	String process ="process  - processes the queue by 1 step.";
	    	String reset = "reset - resets the queue";
	    	String quit = "quit -  exiting the program";
	    	System.out.println("Available Commands: ");
	    	System.out.printf(add +"\r"+ process +"\r"+ reset +"\r"+ quit +"\r"); 
		}
	    public static void handleCommandQuit(String[] args) {
	    	System.out.println("Queue have been exited");
	    	System.exit(0);
		}
	
	    
	    
	    private static void queueRender() {
	 
	    	
	    	
	    String render1 = Arrays.toString(names);
	    String render2 = render1.replace(",", " |");
	    render2 = render2.replace("[", "| " );
	    render2 = render2.replace("]", " |")	;
	  
	    System.out.println("checkout: "+  render2);
	    }
	
	    	
	    

			public static void main(String[] args) {

	    	
	    	names = new String[] {EMPTY_SLOT, EMPTY_SLOT, EMPTY_SLOT, EMPTY_SLOT, EMPTY_SLOT, EMPTY_SLOT, EMPTY_SLOT,EMPTY_SLOT};
	    	queueRender();
	    	
	        Scanner scanner = new Scanner(System.in); 
	     
	      
	        while (true) {
	        	 
	        	System.out.print("> " );
	           
	            
	        	
	            String userInput = scanner.nextLine(); // use nextLine() instead of next() to get the entire line including spaces
	           
	            Command command = parseCommand(userInput); // extract the appropriate Command from the input string
	            
	            if (command == Command.UNKNOWN) { // could not find an appropriate command
	            	System.out.println("Unknown command. Try again. type help for command list");
	            	continue; // jump directly to start of loop without doing anything
	            }
	            
	            // arguments will be [<argument1>, <argument2>, ...]
	            // possible for array to be an empty array []
	            String[] arguments = parseArguments(userInput); // get a list of the optional arguments 
	            
	            if (command == Command.ADD) {
	                handleCommandAdd(arguments); // Adds Names to Queue
	            } else if (command == Command.PROCESS) {
	            	handleCommandProcess(arguments); // Moves the name forward in Queue
	            } else if (command == Command.RESET) {
	            	handleCommandReset(arguments); //Resets the Queue to EMPTY_SLOT
	            } else if (command == Command.HELP) {
	            	handleCommandHelp(arguments); // Posts all commands
	            } else if (command == Command.QUIT) {
	            	handleCommandQuit(arguments);
	            }  queueRender();
	            	 
	            }
	        

	        	
			}


			}


