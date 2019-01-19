def dijkstra(graph,src):
    # 判断图是否为空，如果为空直接退出
    if graph is None:
        return None
    nodes = [i for i in range(len(graph))]  # 获取图中所有节点
    visited=[]  # 表示已经路由到最短路径的节点集合
    if src in nodes:
        visited.append(src)
        nodes.remove(src)
    else:
        return None
    distance={src:0}  # 记录源节点到各个节点的距离
    for i in nodes:
        distance[i]=graph[src][i]  # 初始化
    # print(distance)
    path={src:[src]}  # 记录源节点到每个节点的路径
    #print(path)
    k=pre=src
    while nodes:
        mid_distance=float('inf')
        for v in visited:
            for d in nodes:
                new_distance = distance[v]+graph[v][d]
                if new_distance < distance[d]:
                    distance[d] = new_distance
                if new_distance < mid_distance:
                    mid_distance=new_distance
                    #graph[src][d]=new_distance  # 进行距离更新
                    k=d
                    pre=v
        #distance[k]=mid_distance  # 最短路径
        path[k]=[i for i in path[pre]]
        path[k].append(k)
        # 更新两个节点集合
        if k not in nodes:
            break
        visited.append(k)
        nodes.remove(k)
        print(visited,nodes)  # 输出节点的添加过程
        #print(visited)
    return distance,path
if __name__ == '__main__':
    graph = [[0, 4, 6, 6, float('inf'), float('inf'), float('inf')],
             [float('inf'), 0, 1, float('inf'), 7, float('inf'), float('inf')],
             [float('inf'), float('inf'), 0, float('inf'), 6, 4, float('inf')],
             [float('inf'), float('inf'), 2, 0, float('inf'), 5, float('inf')],
             [float('inf'), float('inf'), float('inf'), float('inf'), 0, float('inf'), 6],
             [float('inf'), float('inf'), float('inf'), float('inf'), 1, 0, 8],
             [float('inf'), float('inf'), float('inf'), float('inf'), float('inf'), float('inf'), 0]
             ]

    distance,path= dijkstra(graph, 2)  # 查找从源点0开始带其他节点的最短路径
    print(distance)
    print (path)
