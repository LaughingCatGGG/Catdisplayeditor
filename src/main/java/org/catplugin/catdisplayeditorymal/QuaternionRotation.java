package org.catplugin.catdisplayeditorymal;

import org.joml.Quaternionf;
import org.joml.Vector3f;

public class QuaternionRotation {

    // 四元数乘法函数
//    static double[] quaternionMultiplication(double[] q1, double[] q2) {
//        double[] result = new double[4];
//        result[0] = q1[0]*q2[0] - q1[1]*q2[1] - q1[2]*q2[2] - q1[3]*q2[3];
//        result[1] = q1[0]*q2[1] + q1[1]*q2[0] + q1[2]*q2[3] - q1[3]*q2[2];
//        result[2] = q1[0]*q2[2] - q1[1]*q2[3] + q1[2]*q2[0] + q1[3]*q2[1];
//        result[3] = q1[0]*q2[3] + q1[1]*q2[2] - q1[2]*q2[1] + q1[3]*q2[0];
//        return result;
//    }

    // 将坐标转换为四元数形式
//    static double[] coordinateToQuaternion(double x, double y, double z) {
//        return new double[]{0, x, y, z};
//    }

    // 将四元数转换为坐标形式
//    static double[] quaternionToCoordinate(double[] q) {
//        return new double[]{q[1], q[2], q[3]};
//    }

    // 主函数，实现旋转功能
        //private static final double EPSILON = 1e-10; // 用于比较浮点数是否相等的小阈值

        public static Vector3f getlo(Vector3f tr, Quaternionf lelo, Quaternionf rolo) {
            // 立方体原始中心坐标
            double[] center = {tr.x()/2, tr.y()/2, tr.z()/2};

            // 第一次旋转四元数
            double[] q1 =  { lelo.x(), lelo.y(), lelo.z(),lelo.w()};
            // 第二次旋转四元数
            double[] q2 = { rolo.x(), rolo.y(), rolo.z(),rolo.w()};

            // 应用第一次旋转
            center = rotate(center, q1);
            // 应用第二次旋转
            center = rotate(center, q2);

            // 输出旋转后的中心坐标
            //System.out.println("Rotated center: (" + center[0] + ", " + center[1] + ", " + center[2] + ")");

            return new Vector3f((float) center[0],(float) center[1],(float) center[2]);
        }

        /**
         * 将四元数归一化
         */
        private static double[] normalize(double[] q) {
            double magnitude = Math.sqrt(q[0] * q[0] + q[1] * q[1] + q[2] * q[2] + q[3] * q[3]);
            return new double[]{q[0] / magnitude, q[1] / magnitude, q[2] / magnitude, q[3] / magnitude};
        }

        /**
         * 计算四元数的共轭
         */
        private static double[] conjugate(double[] q) {
            return new double[]{q[0], -q[1], -q[2], -q[3]};
        }

        /**
         * 使用四元数旋转一个向量
         */
        private static double[] rotate(double[] vector, double[] q) {
            q = normalize(q); // 归一化四元数
            double[] qConj = conjugate(q); // 计算共轭四元数
            double[] v = {vector[0], vector[1], vector[2], 0}; // 将向量扩展为四元数形式

            // 应用旋转
            double[] result = multiply(multiply(q, v), qConj);

            // 返回旋转后的向量部分
            return new double[]{result[0], result[1], result[2]};
        }

        /**
         * 四元数乘法
         */
        private static double[] multiply(double[] a, double[] b) {
            return new double[]{
                    a[0] * b[0] - a[1] * b[1] - a[2] * b[2] - a[3] * b[3],
                    a[0] * b[1] + a[1] * b[0] + a[2] * b[3] - a[3] * b[2],
                    a[0] * b[2] - a[1] * b[3] + a[2] * b[0] + a[3] * b[1],
                    a[0] * b[3] + a[1] * b[2] - a[2] * b[1] + a[3] * b[0]
            };
        }
    }
