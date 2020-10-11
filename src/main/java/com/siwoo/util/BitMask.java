package com.siwoo.util;

/**
 * 비트마스트 (Bit Mask)
 *  비트 집합을 이용한 모든 경우의 수 (조합)을 생성. 합
 *  순서가 중요한 경우의 수가 필요할 시 PERMUTATION 을 이용.
 *      => 각 자리의 비트를 하나의 원소로 여김.
 *     1 0 0 0 1 1 1 0 1 0 = {1, 3, 4, 5, 9} 의 집합 => N=10 인 집합의 부분 집
 *
 *      contains? set & 1 << e
 *      add? set | 1 << e
 *      remove? set & ~(1 << e)
 *      toggle? set ^ (1 << e)
 *      전체집합 = (1 << N) -1
 *      공집합 = 0
 *
 *  비트 연산
 *  A  B    ~A  A&B A|B A^B
 *  0  0    1   0   0   0
 *  0  1    1   0   1   1
 *  1  0    0   0   1   1
 *  1  1    0   1   1   0
 *
 *  not 연산시 자료형에 따라 연산의 결과가 달라지므로 주의.
 *      (8비트의 not 과 32비트의 not 은 완전히 다르다)
 *
 *  쉬프트 연산
 *     bit << move (bit * 2^move)
 *     5 << 10 = 5 * 2^10 (1024) = 5120
 *     bit >> move (bit / 2^move)
 *     (A+B) / 2 = (A+B) >> 1
 *
 *  비트 연산의 우선순위.
 *      숫자 연산보다 우선 순위가 낮다.
 */
public class BitMask {

    private int set;

    public BitMask(int set) {
        this.set = set;
    }

    public static BitMask emptySet() {
        return new BitMask(0);
    }

    public static BitMask fullSet(int N) {
        return new BitMask((1 << N-1) - 1);
    }

    public boolean contains(int e) {
        return (set & get(e)) != 0;
    }

    public void add(int e) {
        set = set | get(e);
    }

    public void remove(int e) {
        set = set & ~get(e);
    }

    public void toggle(int e) {
        set = set ^ ~get(e);
    }
    private int get(int e) {
        return 1 << e;
    }

    public static void main(String[] args) {
        BitMask bitMask = BitMask.emptySet();
        bitMask.add(1);
        bitMask.add(3);
        bitMask.add(5);
        bitMask.add(7);
        for (int i=0; i<10; i++)
            System.out.println(i + ": " + bitMask.contains(i));
        System.out.println();
        bitMask.remove(3);
        bitMask.remove(5);
        for (int i=0; i<10; i++)
            System.out.println(i + ": " + bitMask.contains(i));
    }
}
