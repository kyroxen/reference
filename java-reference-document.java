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


// Comparator
private void fun(){
	Comparator<Point> comparator = new Comparator<>(){
		@Override
		public int compare(Point p, Point q){
			return Integer.compare(p.distane, q.distance);
		}
	};
	
	PriorityQueue<Point> maxHeap = new PriorityQueue<>(comparator.reversed());
	maxHeap.offer(somePoint);
	maxHeap.offer(someOtherPoint);
	Point maximumDistancePoint = maxHeap.poll();
}
