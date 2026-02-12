
void main() {
    String[] colors = {
            "red", "orange", "aqua", "pink", "gray",
            "blue", "white", "black", "yellow", "brown"
    };

    HashMap<String,Color> map = new HashMap<>();
    for(String colorName : colors){
        String upperName = colorName.toUpperCase();
        Color color = new Color(upperName);
        map.put(colorName, color);
    }

    int i = 1;
    for(var entry : map.entrySet()) {

    	System.out.println(i + ") " + entry.getKey() + ": " + entry.getValue());
        i++;
    }
}

public  static class Color{
    private String name;

    public Color(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
