# Coding Exercise

## Assumptions

- There same two teams will never play two matches at the same time. Brazil vs Japan can only be once in the scoreboard.
  - Would implement logic at a later stage if this might be a problem. Match id or something.
- In a more detailed solution I would have created my own exceptions to make it more clear what is failing. 
- Assuming a team cannot play against itself (No two teams with the same name)
- Assuming it is possible to fix mistakes in score (score goes from 2-2 to 2-1). Should probably be a warning or something
- 

## Notes
- Not using any AI autocompletion tools
- Not focusing on proper exceptions. Using normal Exception for all cases. Would've created my own exceptions, or at least used other premade exceptions, like IllegalArgumentException etc.
- Was not done in one sitting, rather multiple small sittings, often with other things done in between
- the .orElseThrow() method on the Optional in getMatch(): Wouldve removed this, and done a ifPresent() check and thrown a specialized error if not present. But as mention, I am not focusing too much on exceptions.
-