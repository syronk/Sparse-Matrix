

class SparseMatrix implements SparseInterface{

	Node head;
	private int rows;
	private int cols;
	
	//constructor creates a matrix of the specified size
	SparseMatrix(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		
	}
	
	//default constructor
	public SparseMatrix() {
	
	}


	//Node class for linked list
	static class Node{
		int data;
		int row;
		int col;
		Node next;
		//constructor creates an instance of node
		Node(int row, int col, int data){
			this.row = row;
			this.col = col;
			this.data = data;
			
		}
	}
	
	//empties the linked list by setting the head to null
	public void clear() {
		this.head = null;
	}

	//clears and sets a new size of the linked list
	public void setSize(int numRows, int numCols) {
		
		clear();
		this.rows = numRows;
		this.cols = numCols;
		
	}

	//adds a node to the beginning of the linked list
	public void addElement(int row, int col, int data) {
		//checks if the coordinates of the element are within the matrix
		if(row > this.rows-1 || col > this.cols-1) {
			return;
			
		}
		//if the data to add is 0 it removes the element at that coordinate (making it 0 in a sparse matrix)
		if(data == 0) {
			removeElement(row, col);
		}
		//adds the element to the linked list
		else
		{
		Node node = new Node(row, col, data);
		Node n = head;
		head = node;
		head.next = n;
		}
	}
	
	//finds and returns the Node at (row, col) if it dosen't exist it returns null
	public Node search(int row, int col) {
		//if the linked list is empty return null
		if (head == null) 
			return null;

		Node node = this.head;
		//loops through list to find element
		while(node != null) {

			if ((node.row == row) && (node.col == col)) 
				return node;

			node = node.next;
		}

		return null;
	}
	
	//converts the linked list to a formatted, ordered string
	public String toString() {

		String description = "";
		if(head == null) return "";
		else {
			for (int i = 0; i < this.rows; i++) {
				
				for (int j = 0; j < this.cols; j++) {
					
					Node node = search(i, j);
					if(node != null) {
						description += node.row + " " + node.col + " " + node.data + "\n";
					}
					
				}
					
			}
		}

		return description;
	}

	//removes the node at (row, col)
	public void removeElement(int row, int col) {
		if(row > this.rows-1 || col > this.cols-1) {
			return;
			
		}
		Node node = this.search(row, col);
		Node curr = head;
		//if the node to be removed is null nothing needs to be done
		if(node == null) {
			return;
		}
		//if the node to be removed is the head
		if (curr == node) {
            if (curr.next == null) {
                head = null;
                return;
            }
 
            head = head.next;
 
            return;
        }
 
        //if not the head loop through to find the node
        Node prev = head;
        while (prev.next != null && prev.next != node) {
            prev = prev.next;
        }
 
        // Remove node from Linked List
        prev.next = prev.next.next;
 
        return;
			
	}

	//returns the data at (row, col)
	public int getElement(int row, int col) {
		//if the list is empty return 0
		if (head == null) return 0;
		if(row > this.rows-1 || col > this.cols-1) {
			throw new java.lang.Error("Out of bounds");
			
		}

		Node p = this.head;
		//traverse the linked list and check each node for the coordinates
		while(p != null) {

			if ((p.row == row) && (p.col == col)) return p.data;

			p = p.next;
		}

		return 0;
	}


	//adds two matrices together and returns the final sum
	public SparseInterface addMatrices(SparseInterface matrixToAdd) {
		//checks to see if the matrices have compatible sizes
		if(this.rows == matrixToAdd.getNumRows() & this.cols == matrixToAdd.getNumCols()) {
		SparseMatrix answer = new SparseMatrix(this.rows, this.cols);
		for(int i =0; i < this.rows; i++) {
			for(int j =0; j < this.cols; j++) {
				int data = this.getElement(i, j) + matrixToAdd.getElement(i, j);
				answer.addElement(i, j, data);
			}
		}
		return answer;
		}
		else {
			return null;
		}
	}
	
	


	//returns the number of rows in the sparse matrix
	public int getNumRows() {
		return this.rows;
	}

	//returns the number of columns in the sparse matrix
	public int getNumCols() {
		return this.cols;
	}

	//multiplies two matrices together
	public SparseInterface multiplyMatrices(SparseInterface matrixToMultiply) {
		//checks to see if the two have compatible sizes
		if(this.cols == matrixToMultiply.getNumRows()) {
			SparseMatrix answer = new SparseMatrix(this.rows, matrixToMultiply.getNumCols());
			for(int i =0; i < this.rows; i++) {
				for(int j = 0; j <matrixToMultiply.getNumCols(); j++) {
					int data =0;
					
					for(int k =0; k < this.cols; k++) {
						 data += this.getElement(i, k)* matrixToMultiply.getElement(k, j);
					}
					answer.addElement(i, j, data);
				}
			}
			
			return answer;
		}
		else {
			return null;
		}
		
	}

	
	
}