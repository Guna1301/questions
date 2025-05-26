/*
Implement a JavaScript ES6 module named AdvancedFactorialTool that calculates 
factorials of numbers provided by the user. The implementation should 
integrate the following ES6 features:

1. Implement your own ES6 class named CustomMap to mimic JavaScript's built-in Map.
2. Use the above-created CustomMap explicitly for caching factorial results to 
   avoid redundant calculations.
3. Provide a deepClone function to clone the cache object deeply, protecting the 
   internal state from accidental external modifications.
4. Implement a generator named factorialGenerator(n) which yields factorial 
   results sequentially from 1! to n!.

*/


class CustomMap {
    // write your code here
    constructor(){
        this.map = {};
        this.size = 0;
    }
    set(key,val){
        if(!this.has(key)){
            this.size++;
        }
        this.map[key] = val;
        return this;
    }
    get(key){
        return this.has(key)?this.map[key]:undefined;
    }
    has(key){
        return Object.prototype.hasOwnProperty.call(this.map,key);
    }
    delete(key){
        if(this.has(key)){
            delete this.map[key];
            this.size--;
            return true;
        }
        return false;
        
    }
    clear(){
        this.map = {};
        this.size = 0;
    }
  }
  
  
  const AdvancedFactorialTool = {
      cache : new CustomMap(),
      factorialMemoized(n) {
          if(n==0 || n==1){
              return 1;
          }
          if(this.cache.has(n)){
              return this.cache.get(n);
          }
          const ans = n*this.factorialMemoized(n-1);
          this.cache.set(n,ans);
          return ans;
      },
      
      deepClone(obj) {
          if(obj instanceof CustomMap){
              const clonedMap = new CustomMap();
              for(const key in obj.map){
                  clonedMap.set(key,obj.map[key])
              }
              return clonedMap
          }
         return JSON.parse(JSON.stringify(obj));
      },
      
      *factorialGenerator(n) {
          for(let i=1;i<=n;i++){
              yield this.factorialMemoized(i);
          }
      
      }
  };
  
  
  
  
  
  
  
  
  
  //Testing code
  function testFactorialTool(n) {
    console.log(`Factorial of ${n} (Memoized):`, AdvancedFactorialTool.factorialMemoized(n));
  
    const originalCache = AdvancedFactorialTool.cache;
    const clonedCache = AdvancedFactorialTool.deepClone(originalCache);
    clonedCache.set(n, "modified");
    console.log("Original cache after clone modification:", originalCache.get(n));
  
    console.log(`Factorial sequence up to ${n}:`, [...AdvancedFactorialTool.factorialGenerator(n)]);
  }
  
  const readline = require('readline').createInterface({
    input: process.stdin,
    output: process.stdout
  });
  
  readline.question('Enter a positive integer to calculate factorial: ', input => {
    const n = parseInt(input.trim());
  
    if (isNaN(n) || n <= 0) {
      console.log('Please enter a valid positive integer.');
    } else {
      testFactorialTool(n);
    }
  
    readline.close();
  });
  