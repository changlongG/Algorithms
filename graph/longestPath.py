#搜索权重最大路径
from graph.toposort import toposort

class search:
    def biggest_weight(self,graph,startpoint,weight):
        if startpoint not in graph:
            return (None,None)
        else:
            dist = dict((u,weight[u]) for u in weight)
            # queue for search
            queue = []
            # path for biggest weight
            path = []
            # record previous node
            comefrom = {}
            toposort_seq = toposort(graph)
            for i in range(len(toposort_seq)):
                tempnode = toposort_seq.pop(0)
                if tempnode is startpoint:
                    queue.append(tempnode)
                    break

            while queue:
                currentnode = queue.pop(0)
                queue += graph[currentnode]
                for i in graph[currentnode]:
                    if dist[i]<dist[currentnode]+weight[i]:
                        dist[i] = dist[currentnode]+weight[i]
                        comefrom[i] = currentnode
            pathnode = max(dist,key = dist.get)
            while not pathnode is startpoint:
                path.append(pathnode)
                pathnode = comefrom[pathnode]
            path.append(startpoint)
            #maxdistance = max(dist,key = dist.get)
            maxdistance = max(dist.values())
            return maxdistance,path[::-1]

G = {
 'A':['C','B'],
 'B':['E','D'],
 'C':['F','E'],
 'E':['G'],
 'D':[],
 'F':[],
 'G':[],
}
weight = {
    'A':1,
    'B':3,
    'C':2,
    'D':2,
    'E':1,
    'F':1,
    'G':2,
}
s = search()
start = "B"
maxdistance,path = s.biggest_weight(G,start,weight)
print(maxdistance,path)


