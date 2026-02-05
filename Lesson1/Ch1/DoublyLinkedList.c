//
// Created by Timothy Tsang on 2/3/26.
//

#include <stdio.h>
#include <stdlib.h>

typedef struct node {
    int data;
    struct node *next;
    struct node *prev;
} Node;

Node* createNode(int data) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode -> data = data;
    newNode -> next = NULL;
    newNode -> prev = NULL;
    return newNode;
}

void insertAtFront(Node** head, int data) {
    Node* newNode = createNode(data);

    if (*head == NULL) {
        *head = newNode;
        return;
    }
    newNode -> next = *head;
    (*head) -> prev = newNode;
    *head = newNode;
}

void insertAtEnd(Node** head, int data) {
    Node* newNode = createNode(data);

    if (*head == NULL) {
        *head = newNode;
        return;
    }

    Node* temp = *head;
    while (temp -> next != NULL) {
        temp = temp -> next;

    }
    temp -> next = newNode;
    newNode -> prev = temp;

}

Node* findNode(Node* head, int target) {
    Node* curr = head;

    while (curr != NULL) {
        if (curr -> data == target) {
            printf("Found node with value %d\n", curr -> data);
            return curr;
        }
        curr = curr -> next;
    }
    printf("Not found\n");
    return NULL;
}

int deleteNode(Node** head, int target) {
    if (*head == NULL) return 0;

    Node* curr = *head;

    while (curr != NULL) {
        if (curr -> data == target) {

            if (curr == *head) {
                *head = curr -> next;
            }

            //Node after the one we're deleting
            if (curr -> next != NULL) {
                curr -> next -> prev = curr -> prev;
            }

            //Node before the one we're deleting
            if (curr -> prev != NULL) {
                curr -> prev -> next = curr -> next;
            }

            free(curr);
            return 1;
        }
       curr = curr -> next;
    }
    return 0;
}

void printList(Node* head)
{
    Node* temp = head;
    while (temp != NULL) {
        printf("%d ", temp->data);
        temp = temp->next;
    }
    printf("\n");
}

int main() {

    Node* head = NULL;
    insertAtFront(&head, 67);
    insertAtFront(&head,68);
    insertAtEnd(&head, 32);
    printList(head);

    printf("Removing node with value 68");
    printf("\n");
    deleteNode(&head, 68);
    printList(head);

    printf("Inserting nodes in front of head");
    printf("\n");
    insertAtFront(&head, 62);
    insertAtFront(&head, 61);
    insertAtFront(&head, 60);
    insertAtFront(&head, 59);
    printList(head);

    printf("Finding node with value of 60");
    printf("\n");
    findNode(head, 60);
    printf("Testing with nonexistent node (42)");
    printf("\n");
    findNode(head, 42);

    printList(head);

}