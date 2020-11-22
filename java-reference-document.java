@Override
public boolean equals(Point another){
	//To check If these objects are same
	if(another == this)
		return true;
	
	//To check if another is an instance of Point
	if(!(another instanceof Point))
		return false;

	//Logic for equality
	Point p = (Point) another;
    if(p.coord == this.coord)
    	return true;
            
    if(p.coord[0] == this.coord[0] && p.coord[1] == this.coord[1])
    	return true;
	
    return false;
}

@Override
public final int hashCode() {
	int result = 17;
	if (coord != null)
		result = 31 * result + Arrays.hashCode(this.coord);    
	return result;
}
