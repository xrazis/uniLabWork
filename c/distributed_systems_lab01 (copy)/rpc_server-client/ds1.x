struct average{
	int y<100>;
};

struct minmax{
	int y<100>;
};

struct product{
	float a;
	int y<100>;
	float x<100>;
};

program DISTRIBUTED_SYSTEMS {
	version DS_V1 {
		float CASE1(average) = 1;
		minmax CASE2(minmax) = 2;
		product CASE3(product) = 3;
	} = 1;
} = 0x23451111;
