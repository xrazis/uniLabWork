//But wait, there is a better approach: store std::pair<V,K> instead of std::pair<K,V>
// and then you don't need any comparer at all — the standard comparer for std::pair
// would be enough, as it compares first (which is V) first then second which is K:

std::vector<std::pair<V,K>> items;
//...
std::sort(items.begin(), items.end());