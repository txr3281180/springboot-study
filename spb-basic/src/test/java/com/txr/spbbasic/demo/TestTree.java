package com.txr.spbbasic.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 递归调用， 拼装树型结构
 */
public class TestTree {

    public static void main(String[] args) {
        //【模拟数据库查询,获取全部数据】
        TestTree tree = new TestTree();
        List<TreeDTO> list = tree.getList();

        //获取 pid 为空的 TreeDTO (一级父节点)
        TreeDTO treeDTO = list.stream()
                .filter(x -> x.getPid() == null)
                .collect(Collectors.toList()).get(0);

        //调用递归（出入父节点id, 数据源）
        treeDTO.setTreeDto(tree.recursion(treeDTO.getId(),list));
        System.out.println("--->|" + treeDTO.getName());
        //递归打印
        tree.recursionPrint(treeDTO.getTreeDto(), "----|");
    }

    //递归打印
    public void recursionPrint(List<TreeDTO> list,String line){
        for (TreeDTO treeDTO : list) {
            System.out.println(line + treeDTO.getName());
            recursionPrint(treeDTO.getTreeDto(), "     " + line  );
        }
    }

    /**
     * 递归 生成树的方法
     * @param id 第一个父节点的id
     * @param list 所有要组建的树形dto
     * @return
     */
    public List<TreeDTO> recursion(String id, List<TreeDTO> list){
        List<TreeDTO> childer1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            //排除最大的父节点元素(父节点id为null)
            if(list.get(i).getPid() == null) continue;
            //是否存在子节点
            if (list.get(i).getPid().equals(id)) {
                //递归
                list.get(i).setTreeDto(recursion(list.get(i).getId(),list));
                childer1.add(list.get(i));
            }
        }
        return childer1;
    }


    /**
     * 初始化数据
     */
    List<TreeDTO> treelist;

    public List<TreeDTO> getList() { return treelist; }

    public TestTree(){  //模拟数据
        treelist = Arrays.asList(
                new TreeDTO[]{
                   new TreeDTO("1", null, "AA"),
                   new TreeDTO("21", "1", "BB"),
                   new TreeDTO("31", "1", "BA"),
                   new TreeDTO("421", "21", "BC"),
                   new TreeDTO("521", "21", "BD"),
                   new TreeDTO("631", "31", "CA"),
                   new TreeDTO("731", "31", "CB"),
                   new TreeDTO("8731", "731", "DA"),
                }
        );
    }

}

/**
 * 树形DTO
 */
class TreeDTO {
    private String id;
    private String pid;
    private String name;
    private List<TreeDTO> treeDto;


    public List<TreeDTO> getTreeDto() {
        return treeDto;
    }

    public void setTreeDto(List<TreeDTO> treeDto) {
        this.treeDto = treeDto;
    }

    public TreeDTO() {
    }

    @Override
    public String toString() {
        return "TreeDTO{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", name='" + name + '\'' +
                ", treeDto=" + treeDto +
                '}';
    }

    public TreeDTO(String id, String pid, String name) {
        this.id = id;
        this.pid = pid;
        this.name = name;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}