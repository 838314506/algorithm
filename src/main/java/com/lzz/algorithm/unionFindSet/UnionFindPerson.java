package com.lzz.algorithm.unionFindSet;

import java.util.*;

/**
 * 每个学生有3个string类型的属性，cardId(身份证id)、bStationId(B站id)、gitId(githubId)
 * 任何两个学生某个属性相同为同一个人
 * 大量实例中有几个独立的人
 */
public class UnionFindPerson {

    public static class Person{
        String cardId;
        String bStationId;
        String gitId;

        public Person(String cardId,String bStationId,String gitId){
            this.cardId = cardId;
            this.bStationId = bStationId;
            this.gitId = gitId;
        }
    }

    public static class UnionFind{
        List<Person> persons = new ArrayList<>();
        //cardId -> person
        Map<String,Person> cardIdMap;
        //bStationId -> person
        Map<String,Person> bStationIdMap;
        //gitId -> person
        Map<String,Person> gitIdMap;
        //合并后的数据
        List<Person> size = new ArrayList<>();

        public UnionFind(int counts){
            String[] cardIds = new String[]{"1","2","3","4","5"};
            String[] bStationIds = new String[]{"a","b","c","d","e"};
            String[] gitIds = new String[]{"g1","g2","g3","g4","g5"};
            cardIdMap = new HashMap<>();
            bStationIdMap = new HashMap<>();
            gitIdMap = new HashMap<>();
            Random r = new Random();
            for (int i = 0;i < counts;i ++){
                Person p = new Person(cardIds[r.nextInt(cardIds.length)],
                        bStationIds[r.nextInt(bStationIds.length)],
                        gitIds[r.nextInt(gitIds.length)]);
                persons.add(p);
            }
        }

        //TODO 暴力方法实现对数器
        public int personCount1(){
            //先用某个属性来
            int pCount = 0;
            for (int i = 0;i < persons.size();i ++){
                Person p1 = persons.get(i);
                for (int j = 0;j < persons.size();j ++){
                    Person p2 = persons.get(j);
                    if (p1 == p2){
                        continue;
                    }
                    if (p2.cardId != p1.cardId
                            && p2.bStationId != p1.bStationId
                            && p2.gitId != p1.gitId){
                        pCount ++;
                    }
                }
            }
            return pCount;
        }

        public int personCount2(){
            //先用某个属性来
            for (Person p : persons){
                if (!cardIdMap.containsKey(p.cardId)
                && !bStationIdMap.containsKey(p.bStationId)
                && !gitIdMap.containsKey(p.gitId)){
                    cardIdMap.put(p.cardId,p);
                    bStationIdMap.put(p.bStationId,p);
                    gitIdMap.put(p.gitId,p);
                    size.add(p);
                }
            }
            return size.size();
        }

        public static void main(String[] arg){
            UnionFind uf = new UnionFind(10);
//            System.out.println(uf.personCount2());
            System.out.println(uf.personCount1());
        }
    }
}
