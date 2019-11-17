# Brute Force
```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            List<String> l = new ArrayList<>();
            for (int j = i; j < s.length(); j++) {
                if (l.contains(s.substring(j, j + 1))) {
                    break;
                }
                l.add(s.substring(j, j + 1));
                if (l.size() > len)
                    len = l.size();
            }
        }
        return len;
    }
}
```

# Slide Window
1. use HashSet
```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int len = 0, i = 0, j = 0;
        while(i < n && j < n) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                len = Math.max(len, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return len;
    }
}
```

# Slide Window with Optimization
1. use HashMap
```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>(); 
        int len = 0;
        for (int i = 0, j = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            len = Math.max(len, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return len;
    }
}
```

2. use direct access table - int[]
```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int[] index = new int[128];
        int len = 0;
        for (int i = 0, j = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            len = Math.max(len, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return len;
    }
}
```